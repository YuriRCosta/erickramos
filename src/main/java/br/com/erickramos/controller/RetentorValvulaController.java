package br.com.erickramos.controller;

import br.com.erickramos.model.RetentorValvula;
import br.com.erickramos.repository.RetentorValvulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/retentorValvula")
public class RetentorValvulaController {

    @Autowired
    private RetentorValvulaRepository repository;

    @PostMapping
    public ResponseEntity<RetentorValvula> criarRetentorValvula(@RequestBody @Valid RetentorValvula retentorValvula) {
        return ResponseEntity.ok(repository.save(retentorValvula));
    }

    @GetMapping
    public ResponseEntity<List<RetentorValvula>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetentorValvula> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RetentorValvula>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetentorValvula> atualizarRetentorValvula(@PathVariable Long id, @RequestBody @Valid RetentorValvula retentorValvula) {
        return ResponseEntity.ok(repository.save(retentorValvula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRetentorValvula(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
