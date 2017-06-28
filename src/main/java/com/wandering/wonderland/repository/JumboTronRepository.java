package com.wandering.wonderland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandering.wonderland.model.JumbotronContent;


public interface JumboTronRepository extends JpaRepository<JumbotronContent, Long> {
	
	List<JumbotronContent> findById(Long id);
	
}
