package com.curso.poo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.poo.exception.MyFileNotFound;
import com.curso.poo.model.vo.UploadFileResponseVO;
import com.curso.poo.service.CasaFileService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/casa/file/v1")
@Api(value = "Upload e download de arquivos.")
public class CasaFileController {

	@Autowired
	CasaFileService casaFileService;
	
	@PostMapping(value = "/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = casaFileService.uploadFile(file);
		
		String fileNameUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/casa/file/v1/download/").path(fileName).toUriString();
		
		return new UploadFileResponseVO(fileName, fileNameUri, file.getContentType(), file.getSize());
	}
	
	@PostMapping(value = "/uploadFiles")
	public List<UploadFileResponseVO> uploadFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/downlodFile/{fileName:.+}")
	public ResponseEntity<Resource> downlodFile(@PathVariable String fileName, HttpServletRequest request ){
		Resource resource = casaFileService.doenloadFile(fileName);
		
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());				
		} catch (Exception e) {
			throw new MyFileNotFound("Arquivo nao localizado", e);
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
				.body(resource);	
		
	}
}









