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
public class Votacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVotacao;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="IDSESSAO")
	private Sessao sessao;
	
	private String cpf;
	private String voto;
	
	public Votacao() {
		super();
	}
	
	public Votacao(String cpf, Sessao sessao, String voto) {
		this.cpf = cpf;
		this.sessao = sessao;
		this.voto = voto;
	}

	public Long getIdVotacao() {
		return idVotacao;
	}

	public void setIdVotacao(Long idVotacao) {
		this.idVotacao = idVotacao;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}
}
