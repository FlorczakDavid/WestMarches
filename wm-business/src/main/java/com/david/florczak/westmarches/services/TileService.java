package com.david.florczak.westmarches.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.florczak.westmarches.dtos.TileGet;
import com.david.florczak.westmarches.dtos.TileRequest;
import com.david.florczak.westmarches.repositories.TileJPARepository;

@Service
@Transactional(readOnly = true)
public class TileService {
	
	private final TileJPARepository tiles;
	
	public TileService(TileJPARepository tiles) {
		this.tiles = tiles;
	}

	public List<TileGet> getUserTiles(TileRequest input) {
		return tiles.findByMapUserEmailAndMapName(input.email(), input.map());
	}

}