package com.sistema.votacao.model.dto;

import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Totalizador;

public class TotaisDTO {
	private String 	pauta;
	private Integer	totaisVotosSim;
	private Integer	totaisVotosNao;
	
	public TotaisDTO(Sessao sessao, Totalizador totalizador) {
		this.pauta = sessao.getPauta().getNome();
		this.setTotaisVotosSim(totalizador.getTotaisSim());
		this.setTotaisVotosNao(totalizador.getTotaisNao());
	}
	
	public String getPauta() {
		return pauta;
	}
	public void setPauta(String pauta) {
		this.pauta = pauta;
	}
	public Integer getTotaisVotosSim() {
		return totaisVotosSim;
	}
	public void setTotaisVotosSim(Integer totaisVotosSim) {
		this.totaisVotosSim = totaisVotosSim;
	}
	public Integer getTotaisVotosNao() {
		return totaisVotosNao;
	}
	public void setTotaisVotosNao(Integer totaisVotosNao) {
		this.totaisVotosNao = totaisVotosNao;
	}
	
	
}
