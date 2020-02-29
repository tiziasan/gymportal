package it.univaq.disim.mwt.myunivaq.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PrenotazioneAppello {

	private Studente studente;
	private Appello appello;
	private Date dataPrenotazione;
	
}
