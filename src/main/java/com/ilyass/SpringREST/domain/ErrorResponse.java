package com.ilyass.SpringREST.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
}

/**
 * Cette classe permet de personnaliser les messages d’erreurs que nous allons transmettre comme réponse au client au cas où une exception a été levée ou bien au cas où une règle de gestion n’a pas été respectée.
 */
