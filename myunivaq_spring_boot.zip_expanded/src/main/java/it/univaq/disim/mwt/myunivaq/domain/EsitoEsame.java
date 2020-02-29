package it.univaq.disim.mwt.myunivaq.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EsitoEsame {
	
	private Long id;
	private int voto;
	private Date dataVerbalizzazione;
	private Studente studente;
	private Appello appello;
		
}
