package br.com.erickramos.service;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.mapper.custom.ServicoMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ServicosService {

    private static final String SERVICO_NAO_ENCONTRADO = "Serviço não encontrado";

    @Autowired
    ServicoMapper mapper;

    @Autowired
    ServicoRepository repository;

    private final Logger logger = Logger.getLogger(ServicosService.class.getName());

    public ServicoDTO criarNovoServico(ServicoDTO servico) throws ExceptionErickRamos {
        verificacaoServico(servico);
        logger.info("Criando novo serviço: " + servico.getNome());
        var entity = DozerMapper.parseObject(servico, Servico.class);
        return DozerMapper.parseObject(repository.save(entity), ServicoDTO.class);
    }

    public ServicoDTO buscarPorId(Long id) throws ExceptionErickRamos {
        var entity = repository.findById(id).orElseThrow(() -> new ExceptionErickRamos(SERVICO_NAO_ENCONTRADO));
        return DozerMapper.parseObject(entity, ServicoDTO.class);
    }

    public List<ServicoDTO> buscaPorNome(String nome) throws ExceptionErickRamos {
        List<Servico> entity = repository.buscarPorNome(nome);
        if (entity.isEmpty()) {
            throw new ExceptionErickRamos(SERVICO_NAO_ENCONTRADO);
        }
        return DozerMapper.parseListObjects(entity, ServicoDTO.class);
    }

    public ServicoDTO alterarServico(ServicoDTO servicoDTO) throws ExceptionErickRamos {
        verificacaoServico(servicoDTO);
        var entity = DozerMapper.parseObject(servicoDTO, Servico.class);
        return DozerMapper.parseObject(repository.save(entity), ServicoDTO.class);
    }

    public List<ServicoDTO> buscarTodos() {
        return DozerMapper.parseListObjects(repository.findAll(), ServicoDTO.class);
    }

    public void deletarServico(Long id) throws ExceptionErickRamos {
        var entity = repository.findById(id).orElseThrow(() -> new ExceptionErickRamos(SERVICO_NAO_ENCONTRADO));
        repository.delete(entity);
    }

    private void verificacaoServico(ServicoDTO servico) throws ExceptionErickRamos {
        if (servico.getNome() == null || servico.getNome().isEmpty()) {
            throw new ExceptionErickRamos("Nome do serviço não pode ser vazio");
        }
        if (servico.getPreco() == null || servico.getPreco() < 0) {
            throw new ExceptionErickRamos("Preço do serviço não pode ser negativo");
        }
        if (repository.buscarPorNome(servico.getNome()) != null) {
            throw new ExceptionErickRamos("Já existe um serviço com esse nome");
        }
    }
}
