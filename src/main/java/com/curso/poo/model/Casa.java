package com.curso.poo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_casa")
public class Casa {

	@Id
	@SequenceGenerator(name = "seq_casa", sequenceName = "seq_casa", allocationSize = 1)
	@GeneratedValue(generator = "seq_casa", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable = false, length = 200, name = "cor")
	private String cor;
	
	@Column(nullable = false, length = 200, name = "caracteristica")
	private String caracteristica;
	
	@Column(nullable = false)
	private Boolean tem_abits;
}






