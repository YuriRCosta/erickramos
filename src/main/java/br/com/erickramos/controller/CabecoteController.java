package br.com.erickramos.controller;

import br.com.erickramos.model.Cabecote;
import br.com.erickramos.repository.CabecoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cabecote")
public class CabecoteController {

    @Autowired
    private CabecoteRepository repository;

    @PostMapping
    public ResponseEntity<Cabecote> criarCabecote(@RequestBody @Valid Cabecote cabecote) {
        return ResponseEntity.ok(repository.save(cabecote));
    }

    @GetMapping
    public ResponseEntity<List<Cabecote>> buscarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabecote> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cabecote>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cabecote> atualizarCabecote(@PathVariable Long id, @RequestBody @Valid Cabecote cabecote) {
        return ResponseEntity.ok(repository.save(cabecote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCabecote(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
