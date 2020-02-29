package it.univaq.disim.mwt.myunivaq.business;

import java.util.List;

import it.univaq.disim.mwt.myunivaq.domain.AreaSSD;
import it.univaq.disim.mwt.myunivaq.domain.SettoreScientificoDisciplinare;

public interface SSDService {

	ResponseGrid<AreaSSD> findAllAreeSSDPaginated(RequestGrid requestGrid) throws BusinessException;

	void createAreaSSD(AreaSSD areaSSD) throws BusinessException;

	AreaSSD findAreaSSDByID(Long id) throws BusinessException;

	void updateAreaSSD(AreaSSD areaSSD) throws BusinessException;

	void deleteAreaSSD(AreaSSD areaSSD) throws BusinessException;

	List<AreaSSD> findAllAree() throws BusinessException;

	ResponseGrid<SettoreScientificoDisciplinare> findAllSSDPaginated(RequestGrid requestGrid) throws BusinessException;

	void createSSD(SettoreScientificoDisciplinare ssd) throws BusinessException;

	SettoreScientificoDisciplinare findSSDByID(Long id) throws BusinessException;

	void updateSSD(SettoreScientificoDisciplinare ssd) throws BusinessException;

	void deleteSSD(SettoreScientificoDisciplinare ssd) throws BusinessException;

}
