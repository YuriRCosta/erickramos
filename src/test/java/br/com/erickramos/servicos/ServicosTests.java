package br.com.erickramos.servicos;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.repository.ServicoRepository;
import br.com.erickramos.service.ServicosService;
import com.github.dozermapper.core.MappingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServicosTests {

    @Mock
    private ServicoRepository repository;

    @InjectMocks
    private ServicosService servicoService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarNovoServico() throws ExceptionErickRamos, MappingException {
        // Dados de entrada
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Serviço A");
        servico.setPreco(10.0);

        var entity = DozerMapper.parseObject(servico, Servico.class);

        // Simular comportamento do repository
        when(repository.buscarPorNome(servico.getNome())).thenReturn(null);
        when(repository.save(any(Servico.class))).thenReturn(entity);

        // Chamar o método a ser testado
        ServicoDTO result = servicoService.criarNovoServico(servico);

        // Verificar o resultado
        Assert.assertEquals("Serviço A", result.getNome());
        Assert.assertEquals(10.0, result.getPreco(), 0.001);
    }

    @Test
    public void testCriarNovoServicoComNomeVazio() {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("");
        servico.setPreco(50.0);

        // Act & Assert
        Exception exception = Assertions.assertThrows(ExceptionErickRamos.class, () -> {
            servicoService.criarNovoServico(servico);
        });

        // Assert
        String expectedMessage = "Nome do serviço não pode ser vazio";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCriarNovoServicoComPrecoNegativo() {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Teste Servico");
        servico.setPreco(-50.0);

        // Act & Assert
        Exception exception = Assertions.assertThrows(ExceptionErickRamos.class, () -> {
            servicoService.criarNovoServico(servico);
        });

        // Assert
        String expectedMessage = "Preço do serviço não pode ser negativo";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCriarNovoServicoComNomeExistente() throws ExceptionErickRamos {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Teste Servico");
        servico.setPreco(50.0);

        // Mocking repository behavior
        when(repository.buscarPorNome(servico.getNome())).thenReturn(new Servico());

        // Chamar o método a ser testado e verificar a exceção
        Exception exception = assertThrows(ExceptionErickRamos.class, () -> servicoService.criarNovoServico(servico));
        assertEquals("Já existe um serviço com esse nome", exception.getMessage());
    }

}
