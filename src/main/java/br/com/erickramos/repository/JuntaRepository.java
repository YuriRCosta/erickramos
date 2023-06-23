package br.com.erickramos.repository;

import br.com.erickramos.model.Junta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuntaRepository extends JpaRepository<Junta, Long> {

    @Query("SELECT j FROM Junta j WHERE j.cabecotes LIKE %?1%")
    List<Junta> buscarPorNome(String nome);

}
