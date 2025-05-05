package com.david.florczak.westmarches.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.TileGet;
import com.david.florczak.westmarches.entities.Tile;

@Repository
public interface TileJPARepository extends JpaRepository<Tile, Long> {

	List<TileGet> findByMapUserEmailAndMapName(String email, String map);
	
}
