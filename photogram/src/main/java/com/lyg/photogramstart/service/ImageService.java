package com.lyg.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.image.Image;
import com.lyg.photogramstart.repository.ImageRepository;
import com.lyg.photogramstart.web.dto.image.ImageUploadDto;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional 
	public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails ) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지 : "+ imageFileName);
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
	}
}


















