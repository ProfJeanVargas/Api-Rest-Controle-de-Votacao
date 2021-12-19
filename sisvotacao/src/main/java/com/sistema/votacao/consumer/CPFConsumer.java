package com.sistema.votacao.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sistema.votacao.exception.ErrorBadRequest;
import com.sistema.votacao.model.StatusCPF;

@Component
public class CPFConsumer {

	//se retorno = 400 CPF inválido, caso contrário 
	//retorna se habilitado ou não para voto
	public Boolean isCpfValido(Long cpf) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			StatusCPF statusCPF = restTemplate.getForObject("https://user-info.herokuapp.com/users/"+cpf, StatusCPF.class);

			if (statusCPF.getStatus().equals("ABLE_TO_VOTE"))
				return true;
			else
				return false;
		} catch (RestClientException e) {
			throw new ErrorBadRequest("CPF informado é Inválido");
		}
	}
}
