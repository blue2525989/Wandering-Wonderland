package com.wandering.wonderland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandering.wonderland.model.ContactFeatureContent;

public interface ContactFeatureContentRepository extends JpaRepository<ContactFeatureContent, Long> {
	
	List<ContactFeatureContent> findById(Long count);

}
