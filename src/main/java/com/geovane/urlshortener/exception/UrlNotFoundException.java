package com.geovane.urlshortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String shortCode) {
        super("Url não Encontrada no código: "+shortCode) ;
    }
}
