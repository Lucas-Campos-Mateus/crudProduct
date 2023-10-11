package com.example.crudProduct.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice       // Essa anotação é para quando o código for lidar com uma excessão, chamar essa classe aqui
public class RequestsExcepitionHendler {        //Classe que lida com as excepitions

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity threat404(){

        return ResponseEntity.badRequest().body("Dado não encontrado");

    }
}
