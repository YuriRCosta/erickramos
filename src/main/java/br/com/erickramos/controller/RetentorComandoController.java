package br.com.erickramos.controller;

import br.com.erickramos.model.RetentorComando;
import br.com.erickramos.repository.RetentorComandoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/retentorComando")
public class RetentorComandoController {

    @Autowired
    private RetentorComandoRepository repository;

    @PostMapping
    public ResponseEntity<RetentorComando> criarRetentorComando(@RequestBody @Valid RetentorComando retentorComando) {
        return ResponseEntity.ok(repository.save(retentorComando));
    }

    @GetMapping
    public ResponseEntity<List<RetentorComando>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetentorComando> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RetentorComando>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetentorComando> atualizarRetentorComando(@PathVariable Long id, @RequestBody @Valid RetentorComando retentorComando) {
        return ResponseEntity.ok(repository.save(retentorComando));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRetentorComando(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
