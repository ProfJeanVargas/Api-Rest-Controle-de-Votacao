package com.sistema.votacao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sessao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSessao;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="IDPAUTA")
	private Pauta pauta;
	
	private LocalDateTime abertura;
	private Long tempo;
	private LocalDateTime fechamento;
	
	public Sessao() {
		super();
	}

	public Sessao(Pauta pauta, Long tempo) {
		this.pauta = pauta;
		this.tempo = tempo;
		this.abertura = LocalDateTime.now();
	}

	public Long getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public LocalDateTime getAbertura() {
		return abertura;
	}

	public void setAbertura(LocalDateTime abertura) {
		this.abertura = abertura;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public LocalDateTime getFechamento() {
		return fechamento;
	}

	public void setFechamento(LocalDateTime fechamento) {
		this.fechamento = fechamento;
	}

}
