package br.com.erickramos.service;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.mapper.custom.ServicoMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ServicosService {

    @Autowired
    ServicoMapper mapper;

    @Autowired
    ServicoRepository repository;

    private final Logger logger = Logger.getLogger(ServicosService.class.getName());

    public ServicoDTO criarNovoServico(ServicoDTO servico) throws ExceptionErickRamos {
        if (servico.getNome() == null || servico.getNome().isEmpty()) {
            throw new ExceptionErickRamos("Nome do serviço não pode ser vazio");
        }
        if (servico.getPreco() == null || servico.getPreco() < 0) {
            throw new ExceptionErickRamos("Preço do serviço não pode ser negativo");
        }
        if (repository.buscarPorNome(servico.getNome()) != null) {
            throw new ExceptionErickRamos("Já existe um serviço com esse nome");
        }
        logger.info("Criando novo serviço: " + servico.getNome());
        var entity = DozerMapper.parseObject(servico, Servico.class);
        return DozerMapper.parseObject(repository.save(entity), ServicoDTO.class);
    }

    public void alterarPrecos(Servico servico, Double preco) {
        if (preco >= 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        servico.setPreco(preco);
    }

}
