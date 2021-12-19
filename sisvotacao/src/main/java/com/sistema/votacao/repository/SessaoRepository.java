package com.sistema.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.votacao.model.Sessao;


@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
