package com.curso.poo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.converter.DozerConverter;
import com.curso.poo.model.Casa;
import com.curso.poo.model.vo.CasaVo;
import com.curso.poo.repository.CasaRepository;

@Service
public class CasaService {

	@Autowired
	CasaRepository casaRepository;
	
	public CasaVo created(CasaVo casavo) {
		var entity = DozerConverter.parseObject(casavo, Casa.class);
		casaRepository.save(entity);
		return DozerConverter.parseObject(entity, CasaVo.class);
	}

	public CasaVo buscarCasaId(Integer id) {
		var entity = casaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa não localizada!!"));
		return DozerConverter.parseObject(entity, CasaVo.class);
	}
	
	@Transactional
	public CasaVo aplicarAbits(Integer id) {
		casaRepository.aplicarAbits(id);
		var entity = casaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não localizado!!"));
		return DozerConverter.parseObject(entity, CasaVo.class);
	}
	
	public Page<CasaVo> findAllPageable(Pageable pageable) {
		var page = casaRepository.findAll(pageable);
		return page.map(this::convertPageObject);
	}
	
	protected CasaVo convertPageObject(Casa entity) {
		return DozerConverter.parseObject(entity, CasaVo.class);
	}

	public Page<CasaVo> findAllPageableAndCor(String nome, Pageable pageable) {
		var pageCor = casaRepository.buscarCasaCor(nome, pageable);
		return pageCor.map(this::convertPageObject);
	}
	
	
}
