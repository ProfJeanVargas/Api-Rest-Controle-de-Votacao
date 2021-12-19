package com.sistema.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.votacao.model.Pauta;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
