package br.com.fcamara.spring.rest.api.formulario;

import br.com.fcamara.spring.rest.api.modelo.Curso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CriarCursoForm {
    @NotEmpty @NotNull
    private String nome;

    @NotEmpty @NotNull
    private String professor;

    public Curso criarCurso(){
        return new Curso(nome, professor);
    }
}
