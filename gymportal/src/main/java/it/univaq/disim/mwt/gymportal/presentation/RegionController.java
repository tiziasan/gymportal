package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Controller
@RequestMapping("region")

public class RegionController {
	
	
	@Autowired
	private GymBO serviceGym;

	@GetMapping("/Abruzzo")
	public String listAb(Model model) throws BusinessException {
		String abruzzo = "Abruzzo";
		List<Gym> gymList = serviceGym.findByRegion(abruzzo);
		model.addAttribute("region", abruzzo);
        model.addAttribute("gymList", gymList);
		return "/region/index";

	}

	@GetMapping("/Basilicata")
	public String listBa(Model model) throws BusinessException {
		String basilicata = "Basilicata";
		List<Gym> gymList = serviceGym.findByRegion(basilicata);
		model.addAttribute("region", basilicata);
        model.addAttribute("gymList", gymList);
		return "/region/index";

	}

	@GetMapping("/Calabria")
	public String listCa(Model model) throws BusinessException {
		String calabria = "Calabria";
		List<Gym> gymList = serviceGym.findByRegion(calabria);
		model.addAttribute("region", calabria);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Campania")
	public String listCam(Model model) throws BusinessException {
		String campania = "Campania";
		List<Gym> gymList = serviceGym.findByRegion(campania);
		model.addAttribute("region", campania);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Emilia")
	public String listEm(Model model) throws BusinessException {
		String emilia = "Emilia Romagna";
		List<Gym> gymList = serviceGym.findByRegion(emilia);
		model.addAttribute("region", emilia);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Friuli")
	public String listFr(Model model) throws BusinessException {
		String friuli = "Friuli Venezia Giulia";
		List<Gym> gymList = serviceGym.findByRegion(friuli);
		model.addAttribute("region", friuli);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Lazio")
	public String listLa(Model model) throws BusinessException {
		String lazio = "Lazio";
		List<Gym> gymList = serviceGym.findByRegion(lazio);
		model.addAttribute("region", lazio);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Liguria")
	public String listLi(Model model) throws BusinessException {
		String liguria = "Liguria";
		List<Gym> gymList = serviceGym.findByRegion(liguria);
		model.addAttribute("region", liguria);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Lombardia")
	public String listLo(Model model) throws BusinessException {
		String lombardia = "Lombardia";
		List<Gym> gymList = serviceGym.findByRegion(lombardia);
		model.addAttribute("region", lombardia);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Marche")
	public String listMa(Model model) throws BusinessException {
		String marche = "Marche";
		List<Gym> gymList = serviceGym.findByRegion(marche);
		model.addAttribute("region", marche);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Molise")
	public String listMo(Model model) throws BusinessException {
		String molise = "Molise";
		List<Gym> gymList = serviceGym.findByRegion(molise);
		model.addAttribute("region", molise);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Piemonte")
	public String listPi(Model model) throws BusinessException {
		String piemonte = "Piemonte";
		List<Gym> gymList = serviceGym.findByRegion(piemonte);
		model.addAttribute("region", piemonte);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Puglia")
	public String listPu(Model model) throws BusinessException {
		String puglia = "Puglia";
		List<Gym> gymList = serviceGym.findByRegion(puglia);
		model.addAttribute("region", puglia);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Sardegna")
	public String listSa(Model model) throws BusinessException {
		String sardegna = "Sardegna";
		List<Gym> gymList = serviceGym.findByRegion(sardegna);
		model.addAttribute("region", sardegna);
        model.addAttribute("gymList", gymList);
		return "/region/index";

	}

	@GetMapping("/Sicilia")
	public String listSi(Model model) throws BusinessException {
		String sicilia = "Sicilia";
		List<Gym> gymList = serviceGym.findByRegion(sicilia);
		model.addAttribute("region", sicilia);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Toscana")
	public String listTo(Model model) throws BusinessException {
		String toscana = "Toscana";
		List<Gym> gymList = serviceGym.findByRegion(toscana);
		model.addAttribute("region", toscana);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Trentino")
	public String listTr(Model model) throws BusinessException {
		String trentino = "Trentino Alto Adige";
		List<Gym> gymList = serviceGym.findByRegion(trentino);
		model.addAttribute("region", trentino);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Umbria")
	public String listUm(Model model) throws BusinessException {
		String umbria = "Umbria";
		List<Gym> gymList = serviceGym.findByRegion(umbria);
		model.addAttribute("region", umbria);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

	@GetMapping("/Valledaosta")
	public String listVa(Model model) throws BusinessException {
		String valle = "Valle D'Aosta";
		List<Gym> gymList = serviceGym.findByRegion(valle);
		model.addAttribute("region", valle);
        model.addAttribute("gymList", gymList);
		return "/region/index";

	}

	@GetMapping("/Veneto")
	public String listVe(Model model) throws BusinessException {
		String veneto = "Veneto";
		List<Gym> gymList = serviceGym.findByRegion(veneto);
		model.addAttribute("region", veneto);
        model.addAttribute("gymList", gymList);
		return "/region/index";
	}

}
