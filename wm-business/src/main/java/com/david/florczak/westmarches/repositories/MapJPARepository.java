package com.david.florczak.westmarches.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.MapGet;
import com.david.florczak.westmarches.entities.Map;

@Repository
public interface MapJPARepository extends JpaRepository<Map, Long> {

	List<MapGet> findByUserEmail(String email);
	
}
