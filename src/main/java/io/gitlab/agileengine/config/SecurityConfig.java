package io.gitlab.agileengine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/search/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable csrf filter. May need to be enabled in a future story
        http.csrf().disable();

        // enable automatic CORS handling. See CorsConfigurationSource below for implementation details.
        http.cors();
    }
}
