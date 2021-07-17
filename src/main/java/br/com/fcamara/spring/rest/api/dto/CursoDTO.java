package br.com.fcamara.spring.rest.api.dto;

import br.com.fcamara.spring.rest.api.modelo.Curso;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CursoDTO {
    private Integer id;

    private String nome;

    private String professor;

    public static List<CursoDTO> converterLista(List<Curso> cursos){
        return cursos.stream().map(CursoDTO::new).collect(Collectors.toList());
    }

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNomeCurso();
        this.professor = curso.getProfessor();
    }
}
