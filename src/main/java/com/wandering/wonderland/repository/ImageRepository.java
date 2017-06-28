package com.wandering.wonderland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandering.wonderland.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findById(Long count);

	List<Image> findByType(String type);
}
