package com.wandering.wonderland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandering.wonderland.model.ServicesContent;


public interface ServicesRepository extends JpaRepository<ServicesContent, Long> {
	
	List<ServicesContent> findById(Long count);

	List<ServicesContent> findByType(String type);
}
