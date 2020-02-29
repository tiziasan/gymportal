package it.univaq.disim.mwt.myunivaq.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("areessd")
public class AreaSSDController {

	@Autowired
	private SSDService service;

	@GetMapping("/list")
	public String list() {
		return "/areessd/list";
	}

	@PostMapping("/findallpaginated")
	public @ResponseBody ResponseGrid<AreaSSD> findAllPaginated(@RequestBody RequestGrid requestGrid)
			throws BusinessException {
		return service.findAllAreeSSDPaginated(requestGrid);
	}

	@GetMapping("/create")
	public String createStart(Model model) {
		AreaSSD areassd = new AreaSSD();
		model.addAttribute("areassd", areassd);
		return "/areessd/form";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("areassd") AreaSSD areaSSD, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/areessd/form";
		}

		service.createAreaSSD(areaSSD);
		return "redirect:/areessd/list";
	}

	@GetMapping("/update")
	public String updateStart(@RequestParam Long id, Model model) throws BusinessException {
		AreaSSD areassd = service.findAreaSSDByID(id);
		model.addAttribute("areassd", areassd);
		return "/areessd/form";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("areassd") AreaSSD areaSSD, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/areessd/form";
		}
		service.updateAreaSSD(areaSSD);
		return "redirect:/areessd/list";
	}

	@GetMapping("/delete")
	public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		AreaSSD areassd = service.findAreaSSDByID(id);
		model.addAttribute("areassd", areassd);
		return "/areessd/form";
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute("areassd") AreaSSD areaSSD) throws BusinessException {
		service.deleteAreaSSD(areaSSD);
		return "redirect:/areessd/list";
	}

}
