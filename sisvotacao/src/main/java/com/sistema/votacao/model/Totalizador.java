package com.sistema.votacao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Totalizador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTotalizador;
	
	private Sessao sessao;
	private Integer totaisSim;
	private Integer totaisNao;
	
	public Totalizador() {
		super();
	}
	public Totalizador(Sessao sessao, int tSim, int tNao) {
		this.sessao = sessao;
		this.totaisNao = tNao;
		this.totaisSim = tSim;
	}
	
	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	public Integer getTotaisSim() {
		return totaisSim;
	}
	public void setTotaisSim(Integer totaisSim) {
		this.totaisSim = totaisSim;
	}
	public Integer getTotaisNao() {
		return totaisNao;
	}
	public void setTotaisNao(Integer totaisNao) {
		this.totaisNao = totaisNao;
	}
}
