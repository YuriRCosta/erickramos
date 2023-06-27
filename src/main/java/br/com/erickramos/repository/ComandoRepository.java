package br.com.erickramos.repository;

import br.com.erickramos.model.Comando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComandoRepository extends JpaRepository<Comando, Long> {

    @Query("SELECT j FROM Comando j WHERE lower(j.nome) LIKE lower(concat('%', ?1,'%'))")
    List<Comando> buscarPorNome(String nome);

}
