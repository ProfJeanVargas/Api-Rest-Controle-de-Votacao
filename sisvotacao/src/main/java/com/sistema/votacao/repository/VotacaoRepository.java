package com.sistema.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Votacao;


@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

	public Votacao findByCpf(String cPF);

	public Votacao findByCpfAndSessao(String cPF, Sessao sessao);

}
