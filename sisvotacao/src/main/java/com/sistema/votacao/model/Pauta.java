package com.sistema.votacao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pauta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes="ID único entidade Pauta")
	private Long idPauta;
	
	@ApiModelProperty(notes="Nome da pauta")
	private String nome;
	
	public Pauta() {
		super();
	}
	
	public Pauta(String nome) {
		this.nome = nome;
	}
	
	public Long getIdPauta() {
		return idPauta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
