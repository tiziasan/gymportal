package it.univaq.disim.mwt.myunivaq.business;

import it.univaq.disim.mwt.myunivaq.domain.Utente;

public interface UtenteService {

	Utente findUtenteByUsername(String username) throws BusinessException;

	void updateProfilo(Utente nuovoProfilo) throws BusinessException;

}
