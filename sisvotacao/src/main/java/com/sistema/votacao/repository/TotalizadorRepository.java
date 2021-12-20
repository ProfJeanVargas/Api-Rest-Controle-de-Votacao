package com.sistema.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Totalizador;


@Repository
public interface TotalizadorRepository extends JpaRepository<Totalizador, Long> {
	@Query("select t from Totalizador t where t.sessao = ?1")
	public Totalizador findBySessaoAndPauta(Sessao sessao);
}
