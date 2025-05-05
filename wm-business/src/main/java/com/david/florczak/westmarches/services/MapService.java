package com.david.florczak.westmarches.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.florczak.westmarches.dtos.MapGet;
import com.david.florczak.westmarches.repositories.MapJPARepository;

@Service
@Transactional(readOnly = true)
public class MapService {
	
	private final MapJPARepository maps;
	
	public MapService(MapJPARepository maps) {
		this.maps = maps;
	}

	public List<MapGet> getUserMaps(String email) {
		return maps.findByUserEmail(email);
	}

}
