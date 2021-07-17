package br.com.fcamara.spring.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroFormularioDTO {
    private String campo;

    private String erro;
}
