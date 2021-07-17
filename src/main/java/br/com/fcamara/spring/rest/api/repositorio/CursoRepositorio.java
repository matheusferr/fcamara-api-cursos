package br.com.fcamara.spring.rest.api.repositorio;

import br.com.fcamara.spring.rest.api.modelo.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepositorio extends CrudRepository<Curso, Integer> {
    List<Curso> findAll();
}
