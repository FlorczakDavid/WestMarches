package com.david.florczak.westmarches.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.EventGet;
import com.david.florczak.westmarches.entities.Events;

@Repository
public interface EventJPARepository extends JpaRepository<Events, Long> {

	List<EventGet> findByPoiTileMapUserEmailAndPoiTileMapNameAndPoiTileXAndPoiTileYAndPoiName(
			String email, 
			String map,
			int x, 
			int y, 
			String poi);

}
