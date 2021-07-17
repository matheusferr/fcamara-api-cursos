package br.com.fcamara.spring.rest.api.tratamento;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class EntidadeJaExiste {
    private MessageSource messageSource;

    public EntidadeJaExiste(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handle(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recuso já cadastrado");
    }
}
