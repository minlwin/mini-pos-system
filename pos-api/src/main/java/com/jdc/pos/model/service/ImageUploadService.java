package com.jdc.pos.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.pos.model.PosBusinessException;

@Service
public class ImageUploadService {

	@Value("${app.images.location}")
	private String imagePath;

	public String upload(int productId, MultipartFile file) {

		var fileName = "prod-%d.%s".formatted(productId, extension(file));
		var path = Path.of(imagePath, fileName);
		
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			throw new PosBusinessException("% can not create at file system.".formatted(fileName));
		}
	}
	
	private String extension(MultipartFile file) {
		var array = file.getOriginalFilename().split("\\.");
		return array[array.length - 1];
	}
}
