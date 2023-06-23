package br.com.erickramos.repository;

import br.com.erickramos.model.Selo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeloRepository extends JpaRepository<Selo, Long> {

    @Query("SELECT j FROM Selo j WHERE j.nome LIKE %?1%")
    List<Selo> buscarPorNome(String nome);

}
