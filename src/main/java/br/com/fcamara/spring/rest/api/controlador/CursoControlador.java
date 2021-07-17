package br.com.fcamara.spring.rest.api.controlador;

import br.com.fcamara.spring.rest.api.dto.AlunoDTO;
import br.com.fcamara.spring.rest.api.dto.CursoDTO;
import br.com.fcamara.spring.rest.api.formulario.AtualizarCursoForm;
import br.com.fcamara.spring.rest.api.formulario.CriarCursoForm;
import br.com.fcamara.spring.rest.api.formulario.MatricularAlunoForm;
import br.com.fcamara.spring.rest.api.modelo.Aluno;
import br.com.fcamara.spring.rest.api.servico.AlunoServico;
import br.com.fcamara.spring.rest.api.servico.CursoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoControlador {
    private CursoServico cursoServico;

    private AlunoServico alunoServico;

    public CursoControlador(CursoServico cursoServico, AlunoServico alunoServico) {
        this.cursoServico = cursoServico;
        this.alunoServico = alunoServico;
    }

    @GetMapping
    public List<CursoDTO> listar(){
        return this.cursoServico.listar();
    }

    @GetMapping("/{id}/alunos")
    public List<AlunoDTO> listarAlunos(@PathVariable Integer id){
        return this.cursoServico.listarALunos(id);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> criar(@RequestBody @Valid CriarCursoForm cursoForm, UriComponentsBuilder uriBuilder){
        CursoDTO curso = this.cursoServico.criar(cursoForm);

        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(curso);
    }

    @PostMapping("/{id}/matricular")
    public CursoDTO matricularAluno(@PathVariable Integer id, @RequestBody @Valid MatricularAlunoForm alunoForm){
        Aluno aluno = this.alunoServico.procurarPorMatricula(alunoForm.getMatricula());

        return this.cursoServico.adicionarAluno(id, aluno);
    }

    @PutMapping("/{id}")
    public CursoDTO atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizarCursoForm cursoForm){
        return this.cursoServico.atualizar(id, cursoForm);
    }

    @DeleteMapping("/{id}/desmatricular")
    public ResponseEntity<?> removerAluno(@PathVariable Integer id, @RequestBody @Valid MatricularAlunoForm alunoForm){
        Aluno aluno = this.alunoServico.procurarPorMatricula(alunoForm.getMatricula());

        this.cursoServico.removerAluno(id, aluno);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        this.cursoServico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
