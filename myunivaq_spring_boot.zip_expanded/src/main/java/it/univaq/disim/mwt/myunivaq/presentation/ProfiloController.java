package it.univaq.disim.mwt.myunivaq.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.myunivaq.business.BusinessException;
import it.univaq.disim.mwt.myunivaq.business.UtenteService;
import it.univaq.disim.mwt.myunivaq.domain.Utente;

@Controller
@RequestMapping("/common/profilo")
public class ProfiloController {

	@Autowired
	private UtenteService service;

	@GetMapping
	public String modificaProfiloStart(Model model) throws BusinessException {
		Utente utente = Utility.getUtente();
		Utente newUtente = service.findUtenteByUsername(utente.getUsername());
		model.addAttribute("profilo", newUtente);

		return "/common/profilo";

	}

	@PostMapping
	public String modificaProfilo(@ModelAttribute Utente nuovoProfilo) throws BusinessException {
		Utente utente = Utility.getUtente();
		nuovoProfilo.setId(utente.getId());
		service.updateProfilo(nuovoProfilo);
		return "redirect:/common/operazioneok";

	}

}
