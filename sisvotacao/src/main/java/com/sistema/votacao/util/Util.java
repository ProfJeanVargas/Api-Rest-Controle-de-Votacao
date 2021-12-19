package com.sistema.votacao.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class Util {
	//retorna o tempo em minutos
	public Long retornaMinutos(LocalDateTime horaInicial, LocalDateTime horaFinal) {
		return 	ChronoUnit.MINUTES.between(horaInicial, horaFinal);
	}
}
