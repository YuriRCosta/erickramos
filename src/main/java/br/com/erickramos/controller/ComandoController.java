package br.com.erickramos.controller;

import br.com.erickramos.model.Comando;
import br.com.erickramos.repository.ComandoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comando")
public class ComandoController {

    @Autowired
    private ComandoRepository repository;

    @PostMapping
    public ResponseEntity<Comando> criarComando(@RequestBody @Valid Comando comando) {
        return ResponseEntity.ok(repository.save(comando));
    }

    @GetMapping
    public ResponseEntity<List<Comando>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comando> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Comando>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comando> atualizarComando(@PathVariable Long id, @RequestBody @Valid Comando comando) {
        return ResponseEntity.ok(repository.save(comando));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComando(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
