package br.com.fcamara.spring.rest.api.formulario;

import br.com.fcamara.spring.rest.api.modelo.Aluno;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CriarAlunoForm {
    @NotEmpty
    @NotNull
    private String nome;

    @NotEmpty
    @NotNull
    private String matricula;

    public Aluno criarAluno(){
        return new Aluno(nome, matricula);
    }
}
