package com.sistema.votacao.resource;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.votacao.exception.ErrorNotFound;
import com.sistema.votacao.model.Pauta;
import com.sistema.votacao.model.Sessao;
import com.sistema.votacao.model.Votacao;
import com.sistema.votacao.repository.PautaRepository;
import com.sistema.votacao.repository.SessaoRepository;
import com.sistema.votacao.rn.VotacaoRN;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class VotacaoResource {
	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private VotacaoRN votacaoRN;
	
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
	@ApiOperation(value="Acrescenta votos a um sessao aberta - SEM VALIDAR CPF")
	public Votacao adicionarVotoV1(@RequestParam String CPF, @RequestParam String voto, @RequestParam Long idSessao) {
		return votacaoRN.v1AdicionaVoto(CPF, voto, idSessao);
	}

	@PostMapping("/v2/votacao/voto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Acrescenta votos a um sessao aberta - COM VALIDAÇÃO DE CPF POR API EXTERNA")
	public Votacao adicionarVotoV2(@RequestParam String CPF, @RequestParam String voto, @RequestParam Long idSessao) {
		return votacaoRN.v2AdicionaVoto(CPF, voto, idSessao);
	}
	
	
}
