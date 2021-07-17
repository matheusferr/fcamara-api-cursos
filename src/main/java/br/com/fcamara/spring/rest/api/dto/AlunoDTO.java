package br.com.fcamara.spring.rest.api.dto;

import br.com.fcamara.spring.rest.api.modelo.Aluno;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class AlunoDTO {
    private Integer id;

    private String nome;

    private String matricula;

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNomeAluno();
        this.matricula = aluno.getMatricula();
    }

    public static List<AlunoDTO> converterLista(List<Aluno> alunos) {
        return alunos.stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDTO alunoDTO = (AlunoDTO) o;
        return Objects.equals(nome, alunoDTO.nome) && Objects.equals(matricula, alunoDTO.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, matricula);
    }
}
