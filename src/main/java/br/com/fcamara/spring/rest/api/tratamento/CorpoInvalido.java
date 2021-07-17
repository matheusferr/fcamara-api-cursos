package br.com.fcamara.spring.rest.api.tratamento;

import br.com.fcamara.spring.rest.api.dto.ErroFormularioDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CorpoInvalido {
    private MessageSource messageSource;

    public CorpoInvalido(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroFormularioDTO>> handle(MethodArgumentNotValidException e) {
        List<ErroFormularioDTO> errosFormulario = new ArrayList<>();

        List<FieldError> errosCampos = e.getBindingResult().getFieldErrors();

        //Lista todos os campos inválidos;
        errosCampos.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errosFormulario.add(new ErroFormularioDTO(fieldError.getField(), message));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errosFormulario);
    }
}
