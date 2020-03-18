package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")

public class RegionController {
	
     
     @GetMapping("/abruzzo")
     public String listAb(Model model) {
 		model.addAttribute("region", "Abruzzo");
		return "/region/index";
    	 
     }
     @GetMapping("/basilicata")
     public String listBa(Model model) {
  		model.addAttribute("region", "Basilicata");
		return "/region/index";
    	 
     }
     @GetMapping("/calabria")
     public String listCal(Model model) {
  		model.addAttribute("region", "Calabria");

		return "/region/index";
    	 
     }
     @GetMapping("/campania")
     public String listCam(Model model) {
  		model.addAttribute("region", "Campania");

		return "/region/index";
    	 
     }
     @GetMapping("/emilia")
     public String listEm(Model model) {
  		model.addAttribute("region", "Emilia Romagna");

		return "/region/index";
    	 
     }
     @GetMapping("/friuli")
     public String listFr(Model model) {
  		model.addAttribute("region", "Friuli-Venezia-Giulia");

		return "/region/index";
    	 
     }
     @GetMapping("/lazio")
     public String listLa(Model model) {
  		model.addAttribute("region", "Lazio");

		return "/region/index";
    	 
     }
     @GetMapping("/liguria")
     public String listLi(Model model) {
  		model.addAttribute("region", "Liguria");

		return "/region/index";
    	 
     }
     @GetMapping("/lombardia")
     public String listLo(Model model) {
  		model.addAttribute("region", "Lombardia");

		return "/region/index";
    	 
     }
     @GetMapping("/marche")
     public String listMa(Model model) {
  		model.addAttribute("region", "Marche");

		return "/region/index";
    	 
     }
     @GetMapping("/molise")
     public String listMo(Model model) {
  		model.addAttribute("region", "Molise");

		return "/region/index";
    	 
     }
     @GetMapping("/piemonte")
     public String listPi(Model model) {
  		model.addAttribute("region", "Piemonte");

		return "/region/index";
    	 
     }
     @GetMapping("/puglia")
     public String listPu(Model model) {
  		model.addAttribute("region", "Puglia");

		return "/region/index";
    	 
     }
     @GetMapping("/sardegna")
     public String listSa(Model model) {
  		model.addAttribute("region", "Sardegna");

		return "/region/index";
    	 
     }
     @GetMapping("/sicilia")
     public String listSi(Model model) {
  		model.addAttribute("region", "Sicilia");

		return "/region/index";
    	 
     }
     @GetMapping("/toscana")
     public String listTo(Model model) {
  		model.addAttribute("region", "Toscana");

		return "/region/index";
    	 
     }
     @GetMapping("/trentino")
     public String listTr(Model model) {
  		model.addAttribute("region", "Trentino Alto Adige");

		return "/region/index";
    	 
     }
     @GetMapping("/umbria")
     public String listUm(Model model) {
  		model.addAttribute("region", "Umbria");

		return "/region/index";
    	 
     }
     @GetMapping("/valledaosta")
     public String listAO(Model model) {
  		model.addAttribute("region", "Valle D'Aosta");

		return "/region/index";
    	 
     }
     @GetMapping("/veneto")
     public String listVe(Model model) {
  		model.addAttribute("region", "Veneto");

		return "/region/index";
    	 
     }
     
     
}
