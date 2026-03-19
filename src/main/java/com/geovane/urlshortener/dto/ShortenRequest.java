package com.geovane.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenRequest(
        @NotBlank(message = "URL vazia não é permitida")
        @URL(message = "URL Inválida")
        String urlOriginal) {
}
