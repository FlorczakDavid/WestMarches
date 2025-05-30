package com.david.florczak.westmarches.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.florczak.westmarches.dtos.TileDeepDetails;
import com.david.florczak.westmarches.dtos.TileGet;
import com.david.florczak.westmarches.dtos.TileIdentifier;
import com.david.florczak.westmarches.dtos.TileRequest;
import com.david.florczak.westmarches.repositories.TileJPARepository;
import com.david.florczak.westmarches.tranformers.TileDeepDetailsTransformer;

@Service
@Transactional(readOnly = true)
public class TileService {
	
	private final TileJPARepository tiles;
	private final TileDeepDetailsTransformer tranformer;
	
	public TileService(TileJPARepository tiles, TileDeepDetailsTransformer tranformer) {
		this.tiles = tiles;
		this.tranformer = tranformer;
	}

	public List<TileGet> getUserTiles(TileRequest input) {
		return tiles.findByMapUserEmailAndMapName(input.email(), input.map());
	}

	public Object getTileDeepDetails(TileIdentifier input) {
		List<TileDeepDetails> output = tiles.getTilesDeepDetails(input.mail(), input.map(), input.x(), input.y());
		if(output.isEmpty()) {
			return output;
		}
		String firstDesc = output.get(0).description();
		if(!output.stream().map(TileDeepDetails::description).allMatch(desc -> desc.equals(firstDesc))) {
			// TODO: throw exception
		}
		return tranformer.transform(output);
	}

}