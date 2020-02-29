package it.univaq.disim.mwt.myunivaq.configuration;

import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Valid
@ConfigurationProperties(prefix = "myunivaq")
public class MyUnivaqProperties {

	private String dateFormat;
}
