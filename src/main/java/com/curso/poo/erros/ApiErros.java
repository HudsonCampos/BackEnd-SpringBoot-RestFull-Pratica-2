package com.curso.poo.erros;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErros {

	private List<String> erros;
	
	public ApiErros(String message) {
		this.erros = Arrays.asList(message);
	}
	
	public ApiErros(List<String> messages) {
		this.erros = messages;
	}
}

