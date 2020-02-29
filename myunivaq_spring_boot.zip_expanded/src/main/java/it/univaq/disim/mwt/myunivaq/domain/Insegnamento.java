package it.univaq.disim.mwt.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Insegnamento {
	
	private Long id;
	private String codice;
	private String denominazione;
	private Lingua lingua;
	private int cfu;
	private TipologiaCredito tipologiaCredito;
	private int periodoInsegnamento;
	private SettoreScientificoDisciplinare ssd;

	private Docente docente;
	private CorsoDiLaurea corsoDiLaurea;
	
	@Setter(AccessLevel.NONE) private Set<Appello> appelli = new HashSet<>();
	
	public void addAppello(Appello appello) {
		appello.setInsegnamento(this);
		appelli.add(appello);
	}
}
