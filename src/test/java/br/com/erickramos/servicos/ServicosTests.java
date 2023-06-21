package br.com.erickramos.servicos;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.mapper.DozerMapper;
import br.com.erickramos.model.Servico;
import br.com.erickramos.model.dto.ServicoDTO;
import br.com.erickramos.repository.ServicoRepository;
import br.com.erickramos.service.ServicosService;
import com.github.dozermapper.core.MappingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServicosTests {

    @Mock
    private ServicoRepository repository;

    @InjectMocks
    private ServicosService servicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //------------------------------CRIAR NOVO SERVIÇO---------------------------------------------

    @Test
    void testCriarNovoServico() throws ExceptionErickRamos, MappingException {
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
        assertEquals("Serviço A", result.getNome());
        assertEquals(10.0, result.getPreco(), 0.001);
    }

    @Test
    void testCriarNovoServicoComNomeVazio() {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("");
        servico.setPreco(50.0);

        // Act & Assert
        Exception exception = Assertions.assertThrows(ExceptionErickRamos.class, () -> servicoService.criarNovoServico(servico));

        // Assert
        String expectedMessage = "Nome do serviço não pode ser vazio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCriarNovoServicoComPrecoNegativo() {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Teste Servico");
        servico.setPreco(-50.0);

        // Act & Assert
        Exception exception = Assertions.assertThrows(ExceptionErickRamos.class, () -> servicoService.criarNovoServico(servico));

        // Assert
        String expectedMessage = "Preço do serviço não pode ser negativo";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCriarNovoServicoComNomeExistente() {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Teste Servico");
        servico.setPreco(50.0);

        // Mocking repository behavior
        when(repository.buscarPorNome(servico.getNome())).thenReturn(new ArrayList<>());

        // Chamar o método a ser testado e verificar a exceção
        Exception exception = assertThrows(ExceptionErickRamos.class, () ->
                servicoService.criarNovoServico(servico));
        assertEquals("Já existe um serviço com esse nome", exception.getMessage());
    }

    //-----------------------------------------------BUSCA POR ID---------------------------------------------------------

    @Test
    void buscarPorId_DeveRetornarServicoDTOQuandoEncontrado() throws ExceptionErickRamos {
        // Arrange
        Long id = 1L;
        Servico servico = new Servico();
        servico.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(servico));

        // Act
        ServicoDTO resultado = servicoService.buscarPorId(id);

        // Assert
        assertEquals(id, resultado.getKey());
    }

    @Test
    void buscarPorId_DeveLancarExceptionQuandoNaoEncontrado() {
        // Arrange
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ExceptionErickRamos.class, () -> servicoService.buscarPorId(id));
    }

    //-----------------------------------------------BUSCA POR NOME-------------------------------------------------

    @Test
    void buscaPorNome_DeveRetornarListaDeServicoDTOQuandoEncontrado() throws ExceptionErickRamos {
        // Arrange
        String nome = "Nome do Serviço";
        Servico servico1 = new Servico();
        servico1.setNome(nome);
        Servico servico2 = new Servico();
        servico2.setNome(nome);
        List<Servico> servicos = new ArrayList<>();
        servicos.add(servico1);
        servicos.add(servico2);

        when(repository.buscarPorNome(nome)).thenReturn(servicos);

        // Act
        List<ServicoDTO> resultado = servicoService.buscaPorNome(nome);

        // Assert
        assertEquals(servicos.size(), resultado.size());
        assertEquals(nome, resultado.get(0).getNome());
        assertEquals(nome, resultado.get(1).getNome());
    }

    @Test
    void buscaPorNome_DeveLancarExceptionQuandoNaoEncontrado() {
        // Arrange
        String nome = "Nome do Serviço";

        when(repository.buscarPorNome(nome)).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(ExceptionErickRamos.class, () -> servicoService.buscaPorNome(nome));
    }

    //-----------------------------------------------ALTERA SERVICO-------------------------------------------------

    @Test
    void alterarServico_DeveAlterarServicoComSucesso() throws ExceptionErickRamos {
        // Arrange
        ServicoDTO servico = new ServicoDTO();
        servico.setNome("Serviço");
        servico.setPreco(10.0);

        var entity = DozerMapper.parseObject(servico, Servico.class);

        when(repository.buscarPorNome(servico.getNome())).thenReturn(null);
        when(repository.save(any(Servico.class))).thenReturn(entity);

        servicoService.criarNovoServico(servico);

        Servico servicoAtt = new Servico();
        servicoAtt.setId(1L);
        servicoAtt.setNome("Nome do Serviço Novo");
        servicoAtt.setPreco(5.0);

        var dto = DozerMapper.parseObject(servicoAtt, ServicoDTO.class);

        when(repository.buscarPorNome(servicoAtt.getNome())).thenReturn(null);
        when(repository.save(any(Servico.class))).thenReturn(servicoAtt);

        // Act
        ServicoDTO resultado = servicoService.alterarServico(dto);

        // Assert
        assertEquals(dto.getNome(), resultado.getNome());
        assertEquals(dto.getPreco(), resultado.getPreco());
    }

    @Test
    void alterarServico_DeveLancarExceptionQuandoNomeVazio() {
        // Arrange
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setNome("");
        servicoDTO.setPreco(10.0);

        // Act & Assert
        ExceptionErickRamos exception = assertThrows(ExceptionErickRamos.class, () ->
                servicoService.alterarServico(servicoDTO));
        assertEquals("Nome do serviço não pode ser vazio", exception.getMessage());
    }

    @Test
    void alterarServico_DeveLancarExceptionQuandoPrecoNegativo() {
        // Arrange
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setNome("Nome do Serviço");
        servicoDTO.setPreco(-10.0);

        // Act & Assert
        ExceptionErickRamos exception = assertThrows(ExceptionErickRamos.class, () ->
                servicoService.alterarServico(servicoDTO));
        assertEquals("Preço do serviço não pode ser negativo", exception.getMessage());
    }

    @Test
    void alterarServico_DeveLancarExceptionQuandoServicoComMesmoNomeExistir() {
        // Arrange
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setNome("Nome do Serviço");
        servicoDTO.setPreco(10.0);

        when(repository.buscarPorNome(servicoDTO.getNome())).thenReturn(new ArrayList<>());

        // Act & Assert
        ExceptionErickRamos exception = assertThrows(ExceptionErickRamos.class, () ->
                servicoService.alterarServico(servicoDTO));
        assertEquals("Já existe um serviço com esse nome", exception.getMessage());
    }

    //-----------------------------------------------EXCLUIR SERVICO-------------------------------------------------

    @Test
    void deletarServico_DeveDeletarServicoComSucesso() throws ExceptionErickRamos {
        // Arrange
        Long id = 1L;
        Servico servico = new Servico();
        when(repository.findById(id)).thenReturn(Optional.of(servico));

        // Act
        servicoService.deletarServico(id);

        // Assert
        verify(repository).delete(servico);
    }

    @Test
    void deletarServico_DeveLancarExceptionQuandoServicoNaoEncontrado() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ExceptionErickRamos.class, () -> servicoService.deletarServico(id));
    }

    //-----------------------------------------------BUSCA TODOS-------------------------------------------------

    @Test
    void buscarTodos_DeveRetornarListaDeServicosDTO() {
        // Arrange
        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico(1L, "Serviço 1", 10.0));
        servicos.add(new Servico(2L, "Serviço 2", 20.0));

        when(repository.findAll()).thenReturn(servicos);

        // Act
        List<ServicoDTO> resultado = servicoService.buscarTodos();

        // Assert
        assertEquals(servicos.size(), resultado.size());
        assertEquals(servicos.get(0).getNome(), resultado.get(0).getNome());
        assertEquals(servicos.get(0).getPreco(), resultado.get(0).getPreco());
        assertEquals(servicos.get(1).getNome(), resultado.get(1).getNome());
        assertEquals(servicos.get(1).getPreco(), resultado.get(1).getPreco());
    }
}
