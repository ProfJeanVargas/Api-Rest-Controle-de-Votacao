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

@Entity
public class Totalizador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTotalizador;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="IDVOTACAO")
	private Votacao votacao;
	
	private Long totaisSim;
	private Long totaisNao;
	
	public Totalizador() {
		super();
	}
		
	public Long getTotaisSim() {
		return totaisSim;
	}
	public void setTotaisSim(Long totaisSim) {
		this.totaisSim = totaisSim;
	}
	public Long getTotaisNao() {
		return totaisNao;
	}
	public void setTotaisNao(Long totaisNao) {
		this.totaisNao = totaisNao;
	}

}
