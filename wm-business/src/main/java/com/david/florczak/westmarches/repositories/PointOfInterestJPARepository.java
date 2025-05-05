package com.david.florczak.westmarches.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.PointOfInterestGet;
import com.david.florczak.westmarches.entities.PointOfInterest;

@Repository
public interface PointOfInterestJPARepository extends JpaRepository<PointOfInterest, Long> {

	List<PointOfInterestGet> findByTileMapUserEmailAndTileMapNameAndTileXAndTileY(String email, String map, int x,
			int y);

}
