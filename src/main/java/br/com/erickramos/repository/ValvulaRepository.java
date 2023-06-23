package br.com.erickramos.repository;

import br.com.erickramos.model.Valvula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ValvulaRepository extends JpaRepository<Valvula, Long> {

    @Query("SELECT j FROM Valvula j WHERE j.nome LIKE %?1%")
    List<Valvula> buscarPorNome(String nome);

}
