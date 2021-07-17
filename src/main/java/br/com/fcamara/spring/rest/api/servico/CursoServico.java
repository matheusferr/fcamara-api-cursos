package br.com.fcamara.spring.rest.api.servico;

import br.com.fcamara.spring.rest.api.dto.AlunoDTO;
import br.com.fcamara.spring.rest.api.modelo.Aluno;
import org.springframework.stereotype.Service;
import br.com.fcamara.spring.rest.api.dto.CursoDTO;
import br.com.fcamara.spring.rest.api.formulario.AtualizarCursoForm;
import br.com.fcamara.spring.rest.api.formulario.CriarCursoForm;
import br.com.fcamara.spring.rest.api.modelo.Curso;
import br.com.fcamara.spring.rest.api.repositorio.CursoRepositorio;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServico {

    private CursoRepositorio cursoRepositorio;

    public CursoServico(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public List<CursoDTO> listar() {
        List<Curso> cursos = this.cursoRepositorio.findAll();

        return CursoDTO.converterLista(cursos);
    }

      public CursoDTO criar(CriarCursoForm cursoForm) {
        return new CursoDTO(this.cursoRepositorio.save(cursoForm.criarCurso()));
    }

    public CursoDTO atualizar(Integer id, AtualizarCursoForm cursoForm) {
        Optional<Curso> cursoExistente = this.cursoRepositorio.findById(id);

        if (cursoExistente.isEmpty()) throw new EntityNotFoundException("Curso não encontrado");

        Curso curso = cursoExistente.get();

        curso.setProfessor(cursoForm.getProfessor());

        return new CursoDTO(this.cursoRepositorio.save(curso));
    }

    public List<AlunoDTO> listarALunos(Integer id) {
        Optional<Curso> cursoExistente = this.cursoRepositorio.findById(id);

        if (cursoExistente.isEmpty()) throw new EntityNotFoundException();

        Curso curso = cursoExistente.get();

        return AlunoDTO.converterLista(curso.getAlunos());
    }

    public CursoDTO adicionarAluno(Integer id, Aluno aluno) {
        Optional<Curso> cursoExistente = this.cursoRepositorio.findById(id);

        if (cursoExistente.isEmpty()) throw new EntityNotFoundException("Curso não encontrado");

        Curso curso = cursoExistente.get();

        curso.getAlunos().add(aluno);

        return new CursoDTO(this.cursoRepositorio.save(curso));
    }

    public void removerAluno(Integer id, Aluno aluno) {
        Optional<Curso> cursoExistente = this.cursoRepositorio.findById(id);

        if (cursoExistente.isEmpty()) throw new EntityNotFoundException("Curso não encontrado");

        Curso curso = cursoExistente.get();

        curso.getAlunos().remove(aluno);

        this.cursoRepositorio.save(curso);
    }

    public void deletar(Integer id) {
        Optional<Curso> curso = this.cursoRepositorio.findById(id);

        if (curso.isEmpty()) throw new EntityNotFoundException("Curso não encontrado");

        this.cursoRepositorio.delete(curso.get());
    }
}
