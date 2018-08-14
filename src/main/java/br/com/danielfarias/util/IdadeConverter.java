package br.com.danielfarias.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class IdadeConverter {
	
	public static String calcularIdade(Date data) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = data.toInstant();
        
        LocalDate nascimento = instant.atZone(defaultZoneId).toLocalDate();
        LocalDate hoje = LocalDate.now();
         
        Period periodo = Period.between(nascimento, hoje);
         
        return periodo.getYears() + " Anos";
	}

}
