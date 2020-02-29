package it.univaq.disim.mwt.myunivaq.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Utente {
	
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private Date dataNascita;
	private String codiceFiscale;
	private String telefono;
	private String matricola;
	private String username;
	private String password;
	
	private Set<Ruolo> ruoli = new HashSet<>();
}
