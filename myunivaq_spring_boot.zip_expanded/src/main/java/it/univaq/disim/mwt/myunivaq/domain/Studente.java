package it.univaq.disim.mwt.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Studente extends Utente {
	
	private CorsoDiLaurea corsoDiLaurea;
	
	private Set<PrenotazioneAppello> prenotazioni = new HashSet<>();
	

}
