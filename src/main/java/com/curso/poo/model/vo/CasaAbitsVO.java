package com.curso.poo.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class CasaAbitsVO extends CasaVo{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean tem_abits;
}
