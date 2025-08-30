package com.david.florczak.westmarches.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.florczak.westmarches.dtos.DetailedPointOfInterest;
import com.david.florczak.westmarches.dtos.DetailedTile;
import com.david.florczak.westmarches.dtos.EventGet;
import com.david.florczak.westmarches.dtos.TileChange;
import com.david.florczak.westmarches.dtos.TileDeepDetails;
import com.david.florczak.westmarches.dtos.TileEditDetails;
import com.david.florczak.westmarches.dtos.TileGet;
import com.david.florczak.westmarches.dtos.TileIdentifier;
import com.david.florczak.westmarches.dtos.TileRequest;
import com.david.florczak.westmarches.entities.Event;
import com.david.florczak.westmarches.entities.PointOfInterest;
import com.david.florczak.westmarches.entities.Tile;
import com.david.florczak.westmarches.enums.ChangeType;
import com.david.florczak.westmarches.repositories.EventJPARepository;
import com.david.florczak.westmarches.repositories.PointOfInterestJPARepository;
import com.david.florczak.westmarches.repositories.TileJPARepository;
import com.david.florczak.westmarches.transformers.TileDeepDetailsTransformer;

@Service
@Transactional(readOnly = true)
public class TileService {

	private final TileJPARepository tiles;
	private final PointOfInterestJPARepository pointsOfInterest;
	private final EventJPARepository events;
	private final TileDeepDetailsTransformer tranformer;
	
	public TileService(TileJPARepository tiles, 
			PointOfInterestJPARepository pointsOfInterest,
			EventJPARepository events, 
			TileDeepDetailsTransformer tranformer) {
		this.tiles = tiles;
		this.pointsOfInterest = pointsOfInterest;
		this.events = events;
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
	
	@Transactional
	public void updateTileDeepDetails(TileEditDetails input) {
	    Tile tile = tiles.findOneByMapUserEmailAndMapNameAndXAndY(
	        input.email(), input.map(), input.x(), input.y()
	    );

	    DetailedTile currentDetails = tranformer.transformToDetailedTile(tiles.getTilesDeepDetails(
	        input.email(), input.map(), input.x(), input.y()));

	    List<TileChange> differences = getTileDeepDetailsDifferences(input.details(), currentDetails);

	    for (TileChange change : differences) {
	        switch (change.type()) {
	            case CREATE -> {
	                if (change.eventName() == null) { 
	                    PointOfInterest poi = new PointOfInterest(change.poiName(), change.poiDescription(), tile);
	                    pointsOfInterest.save(poi);
	                } else { 
	                    PointOfInterest poi = pointsOfInterest
	                        .findByNameAndTile(change.poiName(), tile);
	                    Event event = new Event(change.eventName(), change.eventDescription(), poi);
	                    events.save(event);
	                }
	            }
	            case UPDATE -> {
	                if (change.poiName() == null) { 
	                    tile.setDescription(change.poiDescription());
	                    tiles.save(tile);
	                } else if (change.eventName() == null) { 
	                    PointOfInterest poi = pointsOfInterest
	                        .findByNameAndTile(change.poiName(), tile);
	                    poi.setDescription(change.poiDescription());
	                    pointsOfInterest.save(poi);
	                } else { 
	                    Event event = events.findByPoiTileAndPoiNameAndName(
	                        tile, change.poiName(), change.eventName());
	                    event.setDescription(change.eventDescription());
	                    events.save(event);
	                }
	            }
	            case DELETE -> {
	                if (change.eventName() == null) { 
	                    pointsOfInterest.deleteByNameAndTile(change.poiName(), tile);
	                } else { 
	                    events.deleteByPoiTileAndPoiNameAndName(tile, change.poiName(), change.eventName());
	                }
	            }
	        }
	    }
	}

	
	private List<TileChange> getTileDeepDetailsDifferences(
		    DetailedTile incomingDetails, 
		    DetailedTile currentDetails
		) {
		    List<TileChange> changes = new ArrayList<>();

		    
		    if (!incomingDetails.description().equals(currentDetails.description())) {
		        changes.add(new TileChange(
		            ChangeType.UPDATE, null, incomingDetails.description(), null, null));
		    }

		    Map<String, DetailedPointOfInterest> incomingPois = incomingDetails.pointsOfInterest()
		        .stream().collect(Collectors.toMap(DetailedPointOfInterest::name, poi -> poi));

		    Map<String, DetailedPointOfInterest> currentPois = currentDetails.pointsOfInterest()
		        .stream().collect(Collectors.toMap(DetailedPointOfInterest::name, poi -> poi));

		    
		    for (var entry : incomingPois.entrySet()) {
		        String poiName = entry.getKey();
		        DetailedPointOfInterest incomingPoi = entry.getValue();

		        if (!currentPois.containsKey(poiName)) {
		            
		            changes.add(new TileChange(
		                ChangeType.CREATE, poiName, incomingPoi.description(), null, null));
		            for (EventGet event : incomingPoi.events()) {
		                changes.add(new TileChange(
		                    ChangeType.CREATE, poiName, null, event.name(), event.description()));
		            }
		        } else {
		            
		            DetailedPointOfInterest currentPoi = currentPois.get(poiName);
		            if (!incomingPoi.description().equals(currentPoi.description())) {
		                changes.add(new TileChange(
		                    ChangeType.UPDATE, poiName, incomingPoi.description(), null, null));
		            }

		            Map<String, EventGet> currentEvents = currentPoi.events().stream()
		                .collect(Collectors.toMap(EventGet::name, e -> e));

		            Map<String, EventGet> incomingEvents = incomingPoi.events().stream()
		                .collect(Collectors.toMap(EventGet::name, e -> e));

		            for (var evtEntry : incomingEvents.entrySet()) {
		                String eventName = evtEntry.getKey();
		                EventGet incomingEvent = evtEntry.getValue();

		                if (!currentEvents.containsKey(eventName)) {
		                    changes.add(new TileChange(
		                        ChangeType.CREATE, poiName, null, eventName, incomingEvent.description()));
		                } else {
		                    EventGet currentEvent = currentEvents.get(eventName);
		                    if (!incomingEvent.description().equals(currentEvent.description())) {
		                        changes.add(new TileChange(
		                            ChangeType.UPDATE, poiName, null, eventName, incomingEvent.description()));
		                    }
		                }
		            }
		            
		            for (String eventName : currentEvents.keySet()) {
		                if (!incomingEvents.containsKey(eventName)) {
		                    changes.add(new TileChange(
		                        ChangeType.DELETE, poiName, null, eventName, null));
		                }
		            }
		        }
		    }

		    
		    for (String poiName : currentPois.keySet()) {
		        if (!incomingPois.containsKey(poiName)) {
		            changes.add(new TileChange(ChangeType.DELETE, poiName, null, null, null));
		        }
		    }

		    return changes;
		}


}