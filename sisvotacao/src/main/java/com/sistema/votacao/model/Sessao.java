package com.sistema.votacao.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private DateTime abertura;
	private Long tempo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private DateTime fechamento;
	
	public Sessao() {
		super();
	}

	public Sessao(Pauta pauta, Long tempo) {
		this.pauta = pauta;
		this.tempo = tempo;
		this.setAbertura(DateTime.now());
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

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public DateTime getAbertura() {
		return abertura;
	}

	public void setAbertura(DateTime abertura) {
		this.abertura = abertura;
	}

	public DateTime getFechamento() {
		return fechamento;
	}

	public void setFechamento(DateTime fechamento) {
		this.fechamento = fechamento;
	}
}
