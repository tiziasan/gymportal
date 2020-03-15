package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")

public class RegionController {
     
     @GetMapping("/abruzzo")
     public String listAb() {
		return "/region/index";
    	 
     }
     @GetMapping("/basilicata")
     public String listBa() {
		return "/region/index";
    	 
     }
     @GetMapping("/calabria")
     public String listCal() {
		return "/region/index";
    	 
     }
     @GetMapping("/campania")
     public String listCam() {
		return "/region/index";
    	 
     }
     @GetMapping("/emilia")
     public String listEm() {
		return "/region/index";
    	 
     }
     @GetMapping("/friuli")
     public String listFr() {
		return "/region/index";
    	 
     }
     @GetMapping("/lazio")
     public String listLa() {
		return "/region/index";
    	 
     }
     @GetMapping("/liguria")
     public String listLi() {
		return "/region/index";
    	 
     }
     @GetMapping("/lombardia")
     public String listLo() {
		return "/region/index";
    	 
     }
     @GetMapping("/marche")
     public String listMa() {
		return "/region/index";
    	 
     }
     @GetMapping("/molise")
     public String listMo() {
		return "/region/index";
    	 
     }
     @GetMapping("/piemonte")
     public String listPi() {
		return "/region/index";
    	 
     }
     @GetMapping("/puglia")
     public String listPu() {
		return "/region/index";
    	 
     }
     @GetMapping("/sardegna")
     public String listSa() {
		return "/region/index";
    	 
     }
     @GetMapping("/sicilia")
     public String listSi() {
		return "/region/index";
    	 
     }
     @GetMapping("/toscana")
     public String listTo() {
		return "/region/index";
    	 
     }
     @GetMapping("/trentino")
     public String listTr() {
		return "/region/index";
    	 
     }
     @GetMapping("/umbria")
     public String listUm() {
		return "/region/index";
    	 
     }
     @GetMapping("/valledaosta")
     public String listAO() {
		return "/region/index";
    	 
     }
     @GetMapping("/veneto")
     public String listVe() {
		return "/region/index";
    	 
     }
     
     
}
