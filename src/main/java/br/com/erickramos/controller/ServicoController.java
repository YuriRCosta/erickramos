package br.com.erickramos.controller;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.service.ServicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/servicos")
public class ServicoController {

    @Autowired
    private ServicosService service;

    @PostMapping
    public ServicoDTO criarNovoServico(@RequestBody ServicoDTO servico) throws ExceptionErickRamos {
        return service.criarNovoServico(servico);
    }

}
