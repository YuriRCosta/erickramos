package br.com.erickramos.controller;

import br.com.erickramos.model.Junta;
import br.com.erickramos.repository.JuntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/junta")
public class JuntaController {

    @Autowired
    private JuntaRepository repository;

    @PostMapping
    public ResponseEntity<Junta> criarJunta(@RequestBody @Valid Junta junta) {
        return ResponseEntity.ok(repository.save(junta));
    }

    @GetMapping
    public ResponseEntity<List<Junta>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Junta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Junta>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Junta> atualizarJunta(@PathVariable Long id, @RequestBody @Valid Junta junta) {
        return ResponseEntity.ok(repository.save(junta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJunta(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
