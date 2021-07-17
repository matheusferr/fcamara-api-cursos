package br.com.fcamara.spring.rest.api.repositorio;

import br.com.fcamara.spring.rest.api.modelo.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepositorio extends CrudRepository<Aluno, Integer> {
    List<Aluno> findAll();
    Optional<Aluno> findByMatricula(String matricula);
}
