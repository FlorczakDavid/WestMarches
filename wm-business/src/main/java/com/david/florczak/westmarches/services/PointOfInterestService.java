package com.david.florczak.westmarches.services;

import com.david.florczak.westmarches.dtos.PointOfInterestGet;
import com.david.florczak.westmarches.dtos.PointOfInterestRequest;
import com.david.florczak.westmarches.repositories.PointOfInterestJPARepository;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PointOfInterestService {
	
	private final PointOfInterestJPARepository pointsOfInterest;
	
	public PointOfInterestService(PointOfInterestJPARepository pointsOfInterest) {
		this.pointsOfInterest = pointsOfInterest;
	}

	public List<PointOfInterestGet> getTilePoIs(PointOfInterestRequest input) {
		return pointsOfInterest.findByTileMapUserEmailAndTileMapNameAndTileXAndTileY(input.email(), input.map(), input.x(), input.y());
	}

}
