package io.gitlab.agileengine.controller;

import io.gitlab.agileengine.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private AdminService adminService;

    @Autowired
    private void setSearchService(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "admin/invalidateCache", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> invalidateCache() {
        adminService.invalidateCache();
        return ResponseEntity
                .ok()
                .body("OK");
    }
}
