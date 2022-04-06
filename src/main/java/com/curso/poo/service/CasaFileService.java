package com.curso.poo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.curso.poo.config.FileStorageConfig;
import com.curso.poo.exception.FileStorageException;
import com.curso.poo.exception.MyFileNotFound;

@Service
public class CasaFileService {

	private Path pathFileStorage;
	
	public CasaFileService(FileStorageConfig fileStorageConfig) {
		this.pathFileStorage = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.pathFileStorage);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possivel criar diretorio!", e);
		}
	}
	
	public String uploadFile(MultipartFile file){
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new MyFileNotFound("Arquivo nao esta de acordo!");
			}
			
			Path pathFile = this.pathFileStorage.resolve(fileName);
			Files.copy(file.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Nao foi possivel upload arquivo!" ,e);
		}
	}
	
	public Resource doenloadFile(String fileName) {
		try {
			Path fileNameStorage = this.pathFileStorage.resolve(fileName).normalize();
			Resource resource = new UrlResource(fileNameStorage.toUri());
			
			if(resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFound("O arquivo " + fileName + " não foi localizado!");

			}
		} catch (Exception e) {
			throw new MyFileNotFound("O arquivo " + fileName + " não localizado!", e);
		}
		
	}
	
}





