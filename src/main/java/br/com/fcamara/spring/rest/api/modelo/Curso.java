package br.com.fcamara.spring.rest.api.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nomeCurso;

    private String professor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Aluno> alunos;

    public Curso(String nomeCurso, String professor) {
        this.nomeCurso = nomeCurso;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }
}
