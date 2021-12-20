package com.sistema.votacao.rn;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistema.votacao.exception.ErrorBadRequest;
import com.sistema.votacao.exception.ErrorNotFound;
import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Totalizador;
import com.sistema.votacao.model.dto.TotaisDTO;
import com.sistema.votacao.repository.SessaoRepository;
import com.sistema.votacao.repository.TotalizadorRepository;

@Component
public class PautaRN {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private TotalizadorRepository totalizadorRepository;

	//busca os totais de voto referentes a pauta e sessão de votação
	public TotaisDTO buscaTotaisVotos(Long idSessao, Long idPauta) {
		try {
			Sessao sessao = sessaoRepository.findById(idSessao).get();

			if (sessao.getPauta().getIdPauta() != idPauta) {
				throw new ErrorBadRequest("Pauta não está associada a está sessão de votação");
			}

			Totalizador totalizador = totalizadorRepository.findBySessaoAndPauta(sessao);
			return new TotaisDTO(sessao, totalizador);
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Sessão não encontrada com id " + idSessao);
		}
	}
}
