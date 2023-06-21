package br.com.erickramos;

import br.com.erickramos.exceptions.ExceptionErickRamos;
import br.com.erickramos.service.ServicosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErickRamosApplicationTests {

    @Autowired
    ServicosService servicosService;

    @Test
    void contextLoads() throws ExceptionErickRamos {
    }

}
