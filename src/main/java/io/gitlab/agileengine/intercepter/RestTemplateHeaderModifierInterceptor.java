package io.gitlab.agileengine.intercepter;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

// TODO fix links
// TODO improve RestTemplate injection
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

//    @Value("${api.token}")
    private final String apiToken = "23567b218376f79d9415";

//    @Value("${auth.api.uri}")
    private final String authApiUri = "http://interview.agileengine.com:80/auth";

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        ClientHttpResponse response = execution.execute(request, body);
        if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            request.getHeaders().add("Authorization", getNewToken());
            return execution.execute(request, body);
        }
        return response;
    }

    private String getNewToken() {
        AuthRequest authRequest = AuthRequest.builder()
                .apiKey(apiToken)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> requestEntity = new HttpEntity<>(authRequest, headers);
        ResponseEntity<AuthResponse> response = restTemplate.exchange(authApiUri, HttpMethod.POST, requestEntity, AuthResponse.class);
        return response.getBody().getToken();
    }

    @Builder
    @Data
    static class AuthRequest {
        String apiKey;
    }

    @Data
    static class AuthResponse {
        boolean auth;
        String token;
    }
}
