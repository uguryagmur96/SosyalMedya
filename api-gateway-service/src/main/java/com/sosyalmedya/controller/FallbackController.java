package com.sosyalmedya.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuthService() {
        return ResponseEntity.ok("Auth Service is down");
    }
    @GetMapping("/user")
    public ResponseEntity<String> fallbackUserService() {
        return ResponseEntity.ok("User Service is down");
    }
    @GetMapping("/post")
    public ResponseEntity<String> fallbackPostService() {
        return ResponseEntity.ok("Post Service is down");
    }
}
