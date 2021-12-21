package com.sistema.votacao.util;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.stereotype.Component;

@Component
public class Util {
	//retorna o tempo em minutos
	public int retornaMinutos(DateTime horaInicial, DateTime horaFinal) {
		return 	Minutes.minutesBetween(horaInicial, horaFinal).getMinutes();
	}
}
