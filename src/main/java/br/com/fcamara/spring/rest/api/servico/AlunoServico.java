package br.com.fcamara.spring.rest.api.servico;

import br.com.fcamara.spring.rest.api.dto.AlunoDTO;
import br.com.fcamara.spring.rest.api.formulario.CriarAlunoForm;
import br.com.fcamara.spring.rest.api.modelo.Aluno;
import br.com.fcamara.spring.rest.api.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AlunoServico {
    private AlunoRepositorio alunoRepositorio;

    public AlunoServico(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public AlunoDTO criar(CriarAlunoForm alunoForm) {
        return new AlunoDTO(this.alunoRepositorio.save(alunoForm.criarAluno()));
    }

    public Aluno procurarPorMatricula(String matricula) {
        Optional<Aluno> aluno = this.alunoRepositorio.findByMatricula(matricula);
        if (aluno.isEmpty()) throw new EntityNotFoundException("Aluno não encontrado");

        return aluno.get();
    }

    public void deletar(Integer id) {
        Optional<Aluno> aluno = this.alunoRepositorio.findById(id);
        if (aluno.isEmpty()) throw new EntityNotFoundException("Aluno não encontrado");

        this.alunoRepositorio.delete(aluno.get());
    }
}
