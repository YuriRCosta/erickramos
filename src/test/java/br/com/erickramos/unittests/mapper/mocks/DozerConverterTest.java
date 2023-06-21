package br.com.erickramos.unittests.mapper.mocks;

import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DozerConverterTest {

    MockServico inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockServico();
    }

    @Test
    void parseEntityToVOTest() {
        ServicoDTO output = DozerMapper.parseObject(inputObject.mockEntity(), ServicoDTO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Nome Test0", output.getNome());
        assertEquals(25D, output.getPreco());
    }

    @Test
    void parseEntityListToVOListTest() {
        List<ServicoDTO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), ServicoDTO.class);
        ServicoDTO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Nome Test0", outputZero.getNome());
        assertEquals(25D, outputZero.getPreco());

        ServicoDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Nome Test7", outputSeven.getNome());
        assertEquals(25D, outputSeven.getPreco());
    }

    @Test
    void parseVOToEntityTest() {
        Servico output = DozerMapper.parseObject(inputObject.mockVO(), Servico.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome Test0", output.getNome());
        assertEquals(25D, output.getPreco());
    }

    @Test
    void parserVOListToEntityListTest() {
        List<Servico> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Servico.class);
        Servico outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Nome Test0", outputZero.getNome());
        assertEquals(25D, outputZero.getPreco());

        Servico outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Nome Test7", outputSeven.getNome());
        assertEquals(25D, outputSeven.getPreco());
    }

}
