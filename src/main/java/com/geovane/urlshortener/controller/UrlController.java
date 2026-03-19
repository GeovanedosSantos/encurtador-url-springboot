package com.geovane.urlshortener.controller;

import com.geovane.urlshortener.dto.ShortenRequest;
import com.geovane.urlshortener.entity.Url;
import com.geovane.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }
    @PostMapping("/shorten")
    public ResponseEntity<Url> shorten(@Valid @RequestBody ShortenRequest request) {
        Url url = service.shortenUrl(request.urlOriginal());
        return ResponseEntity.status(201).body(url);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirection(@PathVariable String shortCode) {
        String urlOriginal = service.getUrl(shortCode);
        return ResponseEntity.status(302).location(URI.create(urlOriginal)).build();
    }
}