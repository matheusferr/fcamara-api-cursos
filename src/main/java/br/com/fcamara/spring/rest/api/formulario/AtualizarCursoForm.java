package br.com.fcamara.spring.rest.api.formulario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarCursoForm {
    @NotEmpty
    @NotNull
    private String professor;
}
