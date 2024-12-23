package com.ilyass.SpringREST.controller;

import com.ilyass.SpringREST.domain.ErrorResponse;
import com.ilyass.SpringREST.service.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Article values incorrect", details);
        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/**
 *     • L’annotation @ControllerAdvice permet à Spring de créer un contrôleur qui va intercepter les exceptions levées par l’application. Le type d’exception à traiter est précisé en paramètre de l’annotation @ExceptionHandler.
 *     • Dans cet exemple, la méthode handleBusinessException(..) traite les exceptions de type BusinessException et la méthode handleOtherExceptions(..) traite les autres exceptions.
 *     • Remarquez que nous avons hérité de la classe ResponseEntityExceptionHandler et ceci afin de redéfinir la méthode handleMethodArgumentNotValid. Cette dernière permet de traiter les exceptions levées par l’api Bean Validation.
 *     • Avec l’annotation @ControllerAdvice, Spring implémente le Design Pattern AOP. Il permet de séparer le traitement technique relatif à la gestion des exceptions du code métier de l’application.
 */
