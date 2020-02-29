package it.univaq.disim.mwt.myunivaq.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class Docente extends Utente {

	@Setter(AccessLevel.NONE)
	private Set<Insegnamento> insegnamenti = new HashSet<>();

	public void addInsegnamento(Insegnamento insegnamento) {
		insegnamento.setDocente(this);
		insegnamenti.add(insegnamento);
	}

}
