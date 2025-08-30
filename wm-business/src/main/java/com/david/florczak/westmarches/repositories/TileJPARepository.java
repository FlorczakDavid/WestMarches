package com.david.florczak.westmarches.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.TileDeepDetails;
import com.david.florczak.westmarches.dtos.TileGet;
import com.david.florczak.westmarches.entities.Tile;

@Repository
public interface TileJPARepository extends JpaRepository<Tile, Long> {

	List<TileGet> findByMapUserEmailAndMapName(String email, String map);

	@Query("""
			  SELECT new com.david.florczak.westmarches.dtos.TileDeepDetails(
			    t.description,
			    p.name,
			    p.description,
			    e.name,
			    e.description
			  )
			  FROM Tile t
			    LEFT JOIN PointOfInterest p ON p.tile = t
			    LEFT JOIN Event e ON e.poi = p
			  WHERE
			    t.map.user.email = ?1
			    AND t.map.name = ?2
			    AND t.x = ?3
			    AND t.y = ?4
			""")
	List<TileDeepDetails> getTilesDeepDetails(String email, String mapName, int x, int y);

	Tile findOneByMapUserEmailAndMapNameAndXAndY(String email, String map, int x, int y);

	
}
