package br.com.fcamara.spring.rest.api.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Alunos")
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeAluno;

    @Column(unique = true)
    private String matricula;

    public Aluno(String nomeAluno, String matricula) {
        this.nomeAluno = nomeAluno;
        this.matricula = matricula;
    }
}
