package com.sistema.votacao.resource;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.votacao.exception.ErrorNotFound;
import com.sistema.votacao.model.Pauta;
import com.sistema.votacao.repository.PautaRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class PautaResource {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@GetMapping("/v1/pautas")
	@ApiOperation(value="Retorna toda a lista de pautas")
	public List<Pauta> listaPautas () {
		return pautaRepository.findAll();
	}
	
	@PostMapping("/v1/pautas")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Adiciona uma pauta")
	public Pauta adicionarPauta(@RequestBody String nome) {
		Pauta pauta = new Pauta(nome);
		return pautaRepository.save(pauta);
	}
	
	@GetMapping("/v1/pautas/{id}")
	@ApiOperation(value="Retorna uma pauta especifica por ID")
	public Pauta buscaPauta (@PathVariable Long id) {
		try {
			Pauta p = pautaRepository.findById(id).get();
			return p;
		} catch (NoSuchElementException e) {
			throw new ErrorNotFound("Pauta de id " + id + " não encontrada.");
		}
	}

}
