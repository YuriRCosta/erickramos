package br.com.erickramos.repository;

import br.com.erickramos.model.RetentorValvula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetentorValvulaRepository extends JpaRepository<RetentorValvula, Long> {

    @Query("SELECT j FROM RetentorValvula j WHERE j.nome LIKE %?1%")
    List<RetentorValvula> buscarPorNome(String nome);

}
