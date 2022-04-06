package com.curso.poo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.poo.model.vo.CasaVo;
import com.curso.poo.service.CasaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/api/casa")
@CrossOrigin(origins = "http://localhost:3300/")
@Api(value = "Api Casa", tags = "Api casa deves")
public class CasaController {
	
	@Autowired
	CasaService casaService;
	
	@PostMapping(value = "/created", consumes = {"application/json", "application/xml"}, 
			produces = {"application/json", "application/xml"})
	public CasaVo created(@RequestBody @Valid CasaVo casavo) {		
		return casaService.created(casavo);
	}
	
	@GetMapping(value = "/buscarCasaId/{id}", 
			produces = {"application/json", "application/xml"})
	public CasaVo buscarCasaId(@PathVariable("id") Integer id) {
		var casavo = casaService.buscarCasaId(id);
		return casavo.add(linkTo(methodOn(CasaController.class).buscarCasaId(id)).withSelfRel());	
	}	
	
	@PatchMapping(value = "/AtivarAbits/{id}", consumes = {"application/json", "application/xml"}, 
			produces = {"application/json", "application/xml"})
	public CasaVo aplicarAbits(@PathVariable("id") Integer id) {
		CasaVo vo = casaService.aplicarAbits(id);
		return vo;
	}
	
	@GetMapping()
	public ResponseEntity<Page<CasaVo>> findAllPageable(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "2") Integer limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		
		Page<CasaVo> pageCasa = casaService.findAllPageable(pageable);
		pageCasa
			.stream()
			.map(pc -> pc.add(linkTo(methodOn(CasaController.class).buscarCasaId(pc.getId())).withSelfRel()));
			
		
		return ResponseEntity.ok(pageCasa);		
	}
	
	@GetMapping(value = "/buscaPorCor/{nome}")
	public ResponseEntity<Page<CasaVo>> findAllPageableAndCor(
				@PathVariable("nome") String nome,
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "limit", defaultValue = "2") Integer limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		
		Page<CasaVo> pageCasa = casaService.findAllPageableAndCor(nome, pageable);
		pageCasa
			.stream()
			.map(pc -> pc.add(linkTo(methodOn(CasaController.class).buscarCasaId(pc.getId())).withSelfRel()));
			
		
		return ResponseEntity.ok(pageCasa);		
	}
	
}














