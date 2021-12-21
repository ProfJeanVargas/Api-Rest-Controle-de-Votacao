package com.sistema.votacao.rn;


import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.votacao.consumer.CPFConsumer;
import com.sistema.votacao.exception.ErrorBadRequest;
import com.sistema.votacao.exception.ErrorFound;
import com.sistema.votacao.exception.ErrorNotFound;
import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Totalizador;
import com.sistema.votacao.model.Votacao;
import com.sistema.votacao.repository.SessaoRepository;
import com.sistema.votacao.repository.TotalizadorRepository;
import com.sistema.votacao.repository.VotacaoRepository;
import com.sistema.votacao.util.Util;

@Service
public class VotacaoRN {
	
	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private TotalizadorRepository totalizadorRepository;
	
	@Autowired
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	private Util util;
	
	@Autowired
	CPFConsumer cpfConsumer;
	
	//VERSÃO 1 - persiste um voto sem validação de CPF
	@Transactional(value = TxType.REQUIRED)
	public Votacao v1AdicionaVoto(String cPF, String voto, Long idSessao) {
		validaObrigatoriosVotacao(cPF, voto, idSessao);
		try {
			Sessao sessao = sessaoRepository.findById(idSessao).get();
			validaVotacao(cPF,sessao);
			Votacao votacao = new Votacao(cPF, sessao, voto);
			votacaoRepository.save(votacao);
			persisteTotalVotosPorSessao(sessao, voto);
			return votacao;
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Sessão de id " + idSessao + " não encontrada.");
		}
	}
	
	//VERSÃO 2 - persiste um voto com validação de CPF e COM INTEGRAÇÃO COM SISTEMAS EXTERNOS
	@Transactional(value = TxType.REQUIRED)
	public Votacao v2AdicionaVoto(String cPF, String voto, Long idSessao) {
		validaCPF(cPF);
		return v1AdicionaVoto(cPF, voto, idSessao);
	}
	
	//valida voto pelo usuário, se pode ou não votar nessa sessao
	private void validaVotacao(String CPF, Sessao sessao) {
		//valida se a votação está fechada para votação
		if (sessao.getFechamento() != null)
			throw new ErrorBadRequest("Sessão de votação já encerrada.");

		//valida se a sessao já está em processo de encerramento. Se ainda não foi fechada, encerra
		//if (sessao.getFechamento() == null && util.retornaMinutos(sessao.getAbertura(), LocalDateTime.now()) > sessao.getTempo()) {
		int tempoMinutos = util.retornaMinutos(sessao.getAbertura(), DateTime.now());
		if (sessao.getFechamento() == null &&  tempoMinutos >= sessao.getTempo().intValue()) {
			sessao.setFechamento(DateTime.now());
			sessaoRepository.save(sessao);
			throw new ErrorBadRequest("Sessão de votação já encerrada.");
		}

		//valida se o usuário já votou na sessão
		Votacao votacao = votacaoRepository.findByCpfAndSessao(CPF, sessao);
		if (votacao != null)
			throw new ErrorFound("Usuário de CPF " + CPF + " já votou nessa sessão.");
	}
	
	//persiste o total de votos
	private void persisteTotalVotosPorSessao(Sessao sessao, String voto) {
		try {
			Totalizador totalizador = totalizadorRepository.findBySessaoAndPauta(sessao);
			
			if (totalizador == null)
				totalizador = new Totalizador(sessao, 0, 0);
			
			if (voto.equalsIgnoreCase("SIM"))
				totalizador.setTotaisSim(totalizador.getTotaisSim() + 1);
			else
				totalizador.setTotaisNao(totalizador.getTotaisNao() + 1);

			totalizadorRepository.save(totalizador);
		} catch  (NoSuchElementException e) {
			throw new ErrorNotFound("Totalizador não encontrado.");
		}
	}
	
	//valida parametros obrigatorios
	private void validaObrigatoriosVotacao(String cPF, String voto, Long idSessao) {
		if (cPF == null || voto.isEmpty())
			throw new ErrorNotFound("CPF de preenchimento obrigatório.");

		if (voto == null || voto.isEmpty())
			throw new ErrorNotFound("Voto de preenchimento obrigatório.");
		
		if (!(voto.equalsIgnoreCase("SIM") || voto.equalsIgnoreCase("NAO") || voto.equalsIgnoreCase("NÃO")))
			throw new ErrorBadRequest("String de votação aceitas: SIM / NÃO ou SIM / NAO");

		if (idSessao == null || idSessao == 0)
			throw new ErrorNotFound("Id da sessão de preenchimento obrigatório.");
	}
	
	//integração com sistemas externos.
	//Se Status = 400 indica que é CPF inválido
	private void validaCPF(String CPF) {
		if(!cpfConsumer.isCpfValido(Long.valueOf(CPF)))
			throw new ErrorBadRequest("Usuário está inabilitado para votar nesse momento.");
	}

}
