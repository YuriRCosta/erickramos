package br.com.erickramos.unittests.mapper.mocks;

import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DozerConverterTest {

    MockServico inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockServico();
    }

    @Test
    void parseEntityToVOTest() {
        ServicoDTO output = DozerMapper.parseObject(inputObject.mockEntity(), ServicoDTO.class);
        Assertions.assertEquals(Long.valueOf(0L), output.getKey());
        Assertions.assertEquals("Nome Test0", output.getNome());
        Assertions.assertEquals(25D, output.getPreco());
    }

    @Test
    void parseEntityListToVOListTest() {
        List<ServicoDTO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), ServicoDTO.class);
        ServicoDTO outputZero = outputList.get(0);

        Assertions.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assertions.assertEquals("Nome Test0", outputZero.getNome());
        Assertions.assertEquals(25D, outputZero.getPreco());

        ServicoDTO outputSeven = outputList.get(7);

        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assertions.assertEquals("Nome Test7", outputSeven.getNome());
        Assertions.assertEquals(25D, outputSeven.getPreco());
    }

    @Test
    void parseVOToEntityTest() {
        Servico output = DozerMapper.parseObject(inputObject.mockVO(), Servico.class);
        Assertions.assertEquals(Long.valueOf(0L), output.getId());
        Assertions.assertEquals("Nome Test0", output.getNome());
        Assertions.assertEquals(25D, output.getPreco());
    }

    @Test
    void parserVOListToEntityListTest() {
        List<Servico> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Servico.class);
        Servico outputZero = outputList.get(0);

        Assertions.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assertions.assertEquals("Nome Test0", outputZero.getNome());
        Assertions.assertEquals(25D, outputZero.getPreco());

        Servico outputSeven = outputList.get(7);

        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assertions.assertEquals("Nome Test7", outputSeven.getNome());
        Assertions.assertEquals(25D, outputSeven.getPreco());
    }

}
