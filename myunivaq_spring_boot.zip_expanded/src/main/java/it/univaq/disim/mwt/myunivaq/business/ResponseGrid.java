package it.univaq.disim.mwt.myunivaq.business;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseGrid<R> {

	private String draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<R> data;

}
