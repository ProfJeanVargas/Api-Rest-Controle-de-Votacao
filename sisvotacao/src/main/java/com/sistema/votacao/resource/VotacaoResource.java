package com.sistema.votacao.resource;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.votacao.consumer.CPFConsumer;
import com.sistema.votacao.exception.ErrorBadRequest;
import com.sistema.votacao.exception.ErrorFound;
import com.sistema.votacao.exception.ErrorNotFound;
import com.sistema.votacao.model.Pauta;
import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Votacao;
import com.sistema.votacao.repository.PautaRepository;
import com.sistema.votacao.repository.SessaoRepository;
import com.sistema.votacao.repository.VotacaoRepository;
import com.sistema.votacao.util.Util;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class VotacaoResource {

	@Autowired
	CPFConsumer cpfConsumer;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private Util util;

	@PostMapping("/v1/votacao/sessao")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Abre uma sessão de votação para uma pauta especifica")
	public Sessao adicionarSessao(@RequestParam Long idPauta,  @RequestParam(value="tempo", required = false, defaultValue = "1") Long tempo) {

		try {
			Pauta pauta = pautaRepository.findById(idPauta).get();
			Sessao sessao = new Sessao(pauta, tempo);
			return sessaoRepository.save(sessao);
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Pauta de id " + idPauta + " não encontrada.");
		}
	}

	@PostMapping("/v1/votacao/voto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Acrescenta votos a um sessao aberta")
	public Votacao adicionarVotoV1(@RequestParam String CPF, @RequestParam String voto, @RequestParam Long idSessao) {
		validaObrigatoriosVotacao(CPF, voto, idSessao);

		try {
			Sessao sessao = sessaoRepository.findById(idSessao).get();
			validaVotacao(CPF,sessao);
			return votacaoRepository.save(new Votacao(CPF, sessao, voto));
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Sessão de id " + idSessao + " não encontrada.");
		}
	}

	@PostMapping("/v2/votacao/voto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Acrescenta votos a um sessao aberta")
	public Votacao adicionarVotoV2(@RequestParam String CPF, @RequestParam String voto, @RequestParam Long idSessao) {
		validaObrigatoriosVotacao(CPF, voto, idSessao);
		validaCPF(CPF); 
		try {
			Sessao sessao = sessaoRepository.findById(idSessao).get();
			validaVotacao(CPF,sessao);
			return votacaoRepository.save(new Votacao(CPF, sessao, voto));
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Sessão de id " + idSessao + " não encontrada.");
		}
	}

	//valida parametros obrigatorios
	private void validaObrigatoriosVotacao(String cPF, String voto, Long idSessao) {
		if (cPF == null || voto.isEmpty())
			throw new ErrorNotFound("CPF de preenchimento obrigatório.");

		if (voto == null || voto.isEmpty())
			throw new ErrorNotFound("Voto de preenchimento obrigatório.");

		if (idSessao == null || idSessao == 0)
			throw new ErrorNotFound("Id da sessão de preenchimento obrigatório.");
	}

	//valida voto pelo usuário, se pode ou não votar nessa sessao
	private void validaVotacao(String CPF, Sessao sessao) {
		//valida se a votação está fechada para votação
		if (sessao.getFechamento() != null)
			throw new ErrorBadRequest("Sessão de votação já encerrada.");

		//valida se a sessao já está em processo de encerramento. Se ainda não foi fechada, encerra
		if (sessao.getFechamento() == null && util.retornaMinutos(sessao.getAbertura(), LocalDateTime.now()) > sessao.getTempo()) {
			sessao.setFechamento(LocalDateTime.now());
			sessaoRepository.save(sessao);
			throw new ErrorBadRequest("Sessão de votação já encerrada.");
		}

		//valida se o usuário já votou na sessão
		Votacao votacao = votacaoRepository.findByCpfAndSessao(CPF, sessao);
		if (votacao != null)
			throw new ErrorFound("Usuário de CPF " + CPF + " já votou nessa sessão.");
	}

	//integração com sistemas externos.
	//Se Status = 400 indica que é CPF inválido
	private void validaCPF(String CPF) {
		if(!cpfConsumer.isCpfValido(Long.valueOf(CPF)))
			throw new ErrorBadRequest("Usuário está inabilitado para votar nesse momento.");
	}

}
