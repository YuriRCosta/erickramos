package br.com.erickramos.unittests.mapper.mocks;

import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;

import java.util.ArrayList;
import java.util.List;

public class MockServico {

    public Servico mockEntity() {
        return mockEntity(0);
    }

    public ServicoDTO mockVO() {
        return mockVO(0);
    }

    public List<Servico> mockEntityList() {
        List<Servico> servicos = new ArrayList<Servico>();
        for (int i = 0; i < 14; i++) {
            servicos.add(mockEntity(i));
        }
        return servicos;
    }

    public List<ServicoDTO> mockVOList() {
        List<ServicoDTO> servicosDTO = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            servicosDTO.add(mockVO(i));
        }
        return servicosDTO;
    }

    public Servico mockEntity(Integer number) {
        Servico servico = new Servico();
        servico.setNome("Nome Test" + number);
        servico.setPreco(25D);
        servico.setId(number.longValue());
        return servico;
    }

    public ServicoDTO mockVO(Integer number) {
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setNome("Nome Test" + number);
        servicoDTO.setPreco(25D);
        servicoDTO.setKey(number.longValue());
        return servicoDTO;
    }
    
}
