package br.com.erickramos.precos;

import br.com.erickramos.model.Servico;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrecosTests {

    @Test
    public void criarNovoServico() {
        Servico servico = new Servico();
        servico.setNome("Plaina Cabecote de Aluminio");
        servico.setPreco(70.0);
    }

}
