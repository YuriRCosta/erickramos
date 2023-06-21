package br.com.erickramos.repository;

import br.com.erickramos.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.nome = ?1")
    List<Servico> buscarPorNome(String nome);

}
