package br.com.erickramos.controller;

import br.com.erickramos.model.Valvula;
import br.com.erickramos.repository.ValvulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/valvula")
public class ValvulaController {

    @Autowired
    private ValvulaRepository repository;

    @PostMapping
    public ResponseEntity<Valvula> criarValvula(@RequestBody @Valid Valvula valvula) {
        return ResponseEntity.ok(repository.save(valvula));
    }

    @GetMapping
    public ResponseEntity<List<Valvula>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valvula> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Valvula>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Valvula> atualizarValvula(@PathVariable Long id, @RequestBody @Valid Valvula valvula) {
        return ResponseEntity.ok(repository.save(valvula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarValvula(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
