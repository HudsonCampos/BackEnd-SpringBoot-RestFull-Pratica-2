package com.curso.poo.model.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class CasaVo extends RepresentationModel<CasaVo> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "{campo.casa.obrigatorio}")
	private String cor;
	private String caracteristica;
}
