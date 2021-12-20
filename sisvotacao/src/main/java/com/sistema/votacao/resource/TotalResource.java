package com.sistema.votacao.resource;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.votacao.model.Totalizador;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class TotalResource {
	
	@GetMapping("/totalizador")
	@ApiOperation(value="Retorna a lista de totalizadores de votação de todas as pautas")
	public List<Totalizador> listaTotalizadores() {
		return null;
	}
	
	@GetMapping("/totalizador/{pauta}")
	@ApiOperation(value="Retorna os totais de votos de uma pauta especifica")
	public Totalizador buscaTotalizadorPauta() {
		return null;
	}

}
