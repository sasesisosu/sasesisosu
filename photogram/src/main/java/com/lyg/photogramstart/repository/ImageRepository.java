package com.lyg.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyg.photogramstart.domain.image.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
