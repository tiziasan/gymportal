package it.univaq.disim.mwt.myunivaq.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univaq.disim.mwt.myunivaq.business.BusinessException;
import it.univaq.disim.mwt.myunivaq.business.RequestGrid;
import it.univaq.disim.mwt.myunivaq.business.ResponseGrid;
import it.univaq.disim.mwt.myunivaq.business.SSDService;
import it.univaq.disim.mwt.myunivaq.domain.AreaSSD;
import it.univaq.disim.mwt.myunivaq.domain.SettoreScientificoDisciplinare;

@Controller
@RequestMapping("/ssds")
public class SSDController {

	@Autowired
	private SSDService service;

	@GetMapping("/list")
	public String list() {
		return "/ssds/list";
	}

	@PostMapping("/findallpaginated")
	public @ResponseBody ResponseGrid<SettoreScientificoDisciplinare> findAllPaginated(@RequestBody RequestGrid requestGrid) throws BusinessException {
		return service.findAllSSDPaginated(requestGrid);
	}

	@GetMapping("/create")
	public String createStart(Model model) throws BusinessException {
		SettoreScientificoDisciplinare ssd = new SettoreScientificoDisciplinare();
		ssd.setAreaSSD(new AreaSSD());
		model.addAttribute("ssd", ssd);
		return "/ssds/form";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("ssd") SettoreScientificoDisciplinare ssd, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/ssds/form";
		}

		service.createSSD(ssd);
		return "redirect:/ssds/list";
	}

	@GetMapping("/update")
	public String updateStart(@RequestParam Long id, Model model) throws BusinessException {
		SettoreScientificoDisciplinare ssd = service.findSSDByID(id);
		model.addAttribute("ssd", ssd);
		return "/ssds/form";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("ssd") SettoreScientificoDisciplinare ssd, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/ssds/form";
		}

		service.updateSSD(ssd);
		return "redirect:/ssds/list";
	}

	@GetMapping("/delete")
	public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		SettoreScientificoDisciplinare ssd = service.findSSDByID(id);
		model.addAttribute("ssd", ssd);
		return "/ssds/form";
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute("ssd") SettoreScientificoDisciplinare ssd) throws BusinessException {
		service.deleteSSD(ssd);
		return "redirect:/ssds/list";
	}
	
	/*
	 * Metodo invocato ad ogni richiesta relativa a tale controller. 
	 * Serve per inserire nel modello la lista delle aree che serve per la select nella form di inserimento e modifica
	 * E' necessario poiche' la validazione e' server-side. Pertanto se non va a buon fine 
	 * (vedere i metodi che gestiscono il POST di inserimento e modifica) viene visualizzata di nuovo la form con gli errori.
	 * Ovviamente in questo caso e' necessario far rivedere la select. 
	 * Tale meccanismo evita la replica del codice in ogni if (result.hasErrors()) { 
	 * */
	@ModelAttribute
	public void addAllAree(Model model) throws BusinessException {
		List<AreaSSD> areessd = service.findAllAree();
		model.addAttribute("areessd", areessd);
	}
}
