package com.sistema.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.votacao.model.Pauta;
import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Totalizador;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
	
		//public Totalizador findBySessao(Sessao sessao);
}
