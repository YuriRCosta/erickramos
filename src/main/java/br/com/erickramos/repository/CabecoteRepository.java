package br.com.erickramos.repository;

import br.com.erickramos.model.Cabecote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CabecoteRepository extends JpaRepository<Cabecote, Long> {

    @Query("SELECT j FROM Cabecote j WHERE lower(j.nome) LIKE lower(concat('%', ?1,'%'))")
    List<Cabecote> buscarPorNome(String nome);

}
