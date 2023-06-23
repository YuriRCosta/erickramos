package br.com.erickramos.repository;

import br.com.erickramos.model.RetentorComando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetentorComandoRepository extends JpaRepository<RetentorComando, Long> {

    @Query("SELECT j FROM RetentorComando j WHERE j.nome LIKE %?1%")
    List<RetentorComando> buscarPorNome(String nome);

}
