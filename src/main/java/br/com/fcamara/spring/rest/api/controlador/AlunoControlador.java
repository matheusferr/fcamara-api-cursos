package br.com.fcamara.spring.rest.api.controlador;

import br.com.fcamara.spring.rest.api.dto.AlunoDTO;
import br.com.fcamara.spring.rest.api.formulario.CriarAlunoForm;
import br.com.fcamara.spring.rest.api.servico.AlunoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("alunos")
public class AlunoControlador {
    private AlunoServico alunoServico;

    public AlunoControlador(AlunoServico alunoServico) {
        this.alunoServico = alunoServico;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@RequestBody @Valid CriarAlunoForm alunoForm, UriComponentsBuilder uriBuilder){
        AlunoDTO aluno = this.alunoServico.criar(alunoForm);

        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).body(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        this.alunoServico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
