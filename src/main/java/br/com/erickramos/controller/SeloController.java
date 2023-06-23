package br.com.erickramos.controller;

import br.com.erickramos.model.Selo;
import br.com.erickramos.repository.SeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/selo")
public class SeloController {

    @Autowired
    private SeloRepository repository;

    @PostMapping
    public ResponseEntity<Selo> criarSelo(@RequestBody @Valid Selo selo) {
        return ResponseEntity.ok(repository.save(selo));
    }

    @GetMapping
    public ResponseEntity<List<Selo>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Selo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Selo>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Selo> atualizarSelo(@PathVariable Long id, @RequestBody @Valid Selo selo) {
        return ResponseEntity.ok(repository.save(selo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSelo(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
