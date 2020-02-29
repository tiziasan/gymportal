package it.univaq.disim.mwt.myunivaq.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Appello {
	
	private Long id;
	private Date data;
	private String descrizione;
	private TipologiaEsame tipologiaEsame;
	
	private Insegnamento insegnamento;
	private Set<PrenotazioneAppello> studentiPrenotati = new HashSet<>();
	
}
