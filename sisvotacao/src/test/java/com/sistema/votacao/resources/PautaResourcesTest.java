package com.sistema.votacao.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sistema.votacao.model.Pauta;
import com.sistema.votacao.resource.PautaResource;

@SpringBootTest
public class PautaResourcesTest {
	
	@Autowired
	PautaResource pautaResource;
	
	@Test
	public void buscarPautaPorIDTest() {
		Pauta pauta = pautaResource.buscaPauta(1L);
		assertEquals("1", pauta.getIdPauta().toString());
		assertEquals("teste de tempo", pauta.getNome());
	}
	
	@Test
	public void adicionarPautaTest() {
		Pauta pauta = pautaResource.adicionarPauta("Pauta para cadastro teste");
		assertTrue(pauta.getNome().equalsIgnoreCase("Pauta para cadastro testes"));
	}

}
