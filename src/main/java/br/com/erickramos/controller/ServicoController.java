package br.com.erickramos.controller;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.service.ServicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicosService service;

    @PostMapping
    public ResponseEntity<ServicoDTO> criarNovoServico(@RequestBody @Valid ServicoDTO servico) throws ExceptionErickRamos {
        return ResponseEntity.ok(service.criarNovoServico(servico));
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ServicoDTO> buscarPorId(Long id) throws ExceptionErickRamos {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<List<ServicoDTO>> buscarPorNome(String nome) throws ExceptionErickRamos {
        return ResponseEntity.ok(service.buscaPorNome(nome));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<ServicoDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @DeleteMapping("/deletarServico/{id}")
    public ResponseEntity<String> deletarServico(Long id) throws ExceptionErickRamos {
        service.deletarServico(id);
        return ResponseEntity.ok("Serviço deletado com sucesso");
    }

    @PutMapping("/alterarServico")
    public ResponseEntity<ServicoDTO> alterarServico(@RequestBody @Valid ServicoDTO servicoDTO) throws ExceptionErickRamos {
        return ResponseEntity.ok(service.alterarServico(servicoDTO));
    }

}
