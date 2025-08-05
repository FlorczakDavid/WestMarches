package com.david.florczak.westmarches.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.florczak.westmarches.dtos.EventRequest;
import com.david.florczak.westmarches.repositories.EventJPARepository;

@Service
@Transactional(readOnly = true)
public class EventService {
		
	private final EventJPARepository events;
		
		public EventService(EventJPARepository events) {
			this.events = events;
		}

	public Object getPoIEvent(EventRequest input) {
		return events.findByPoiTileMapUserEmailAndPoiTileMapNameAndPoiTileXAndPoiTileYAndPoiName(
				input.email(), input.map(), input.x(), input.y(), input.poi());
	}

}
