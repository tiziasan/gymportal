package it.univaq.disim.mwt.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CorsoDiLaurea {
	private Long id;
	private String nome;
	private String classe;
	
	private Set<Studente> iscritti = new HashSet<>();
	private Set<Insegnamento> insegnamenti = new HashSet<>();
	
}
