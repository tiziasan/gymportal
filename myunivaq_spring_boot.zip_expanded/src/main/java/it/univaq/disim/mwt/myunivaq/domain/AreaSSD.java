package it.univaq.disim.mwt.myunivaq.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AreaSSD {

	private Long id;
	
	@NotBlank
	@Size(max=20)
	private String codice;
	
	@NotBlank
	@Size(max=200)
	private String denominazione;

	
}
