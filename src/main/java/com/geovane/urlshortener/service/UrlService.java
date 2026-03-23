package com.geovane.urlshortener.service;

import com.geovane.urlshortener.entity.Url;
import com.geovane.urlshortener.exception.UrlNotFoundException;
import com.geovane.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    private String generateCode() {
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            shortCode.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return shortCode.toString();
    }

    private String generateUniqueCode() {
        String shortCode;
        do {
            shortCode = generateCode();
        }
        while(repository.existsByShortCode(shortCode));
        return shortCode;
    }

    public Url shortenUrl(String urlOriginal) {
        String shortCode = generateUniqueCode();

        Url url = new Url();
        url.setShortCode(shortCode);
        url.setUrlOriginal(urlOriginal);

        return repository.save(url);
    }

    public String getUrl(String shortCode) {
        Url url = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        return url.getUrlOriginal();

    }
}
