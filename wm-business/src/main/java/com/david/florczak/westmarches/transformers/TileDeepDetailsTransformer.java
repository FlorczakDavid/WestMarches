package com.david.florczak.westmarches.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.david.florczak.westmarches.dtos.DetailedPointOfInterest;
import com.david.florczak.westmarches.dtos.DetailedTile;
import com.david.florczak.westmarches.dtos.EventGet;
import com.david.florczak.westmarches.dtos.TileDeepDetails;
import com.david.florczak.westmarches.dtos.TileDeepDetailsNested;

@Component
public class TileDeepDetailsTransformer {

	public TileDeepDetailsNested transform(List<TileDeepDetails> output) {
		String description = output.get(0).description();
		ArrayList<HashMap<String, Object>> poiDetails = new ArrayList<>();
		
		ArrayList<String> poiNames = new ArrayList<>();
		for(TileDeepDetails detail: output) {
			if(!poiNames.contains(detail.poiName())) {
				poiNames.add(detail.poiName());
			}
		}
		
		HashMap<String, Object> eventsByPoiName = new HashMap<>();
		for(TileDeepDetails detail: output) {
			if(eventsByPoiName.containsKey(detail.poiName())) {
				ArrayList<HashMap<String, Object>> events = (ArrayList<HashMap<String, Object>>) eventsByPoiName.get(detail.poiName());
				HashMap<String, Object> eventDetails = new HashMap<>();
				eventDetails.put("name", detail.eventName());
				eventDetails.put("description", detail.eventDescription());
				events.add(eventDetails);
			} else {
				ArrayList<HashMap<String, Object>> events = new ArrayList<>();
				HashMap<String, Object> eventDetails = new HashMap<>();
				eventDetails.put("name", detail.eventName());
				eventDetails.put("description", detail.eventDescription());
				events.add(eventDetails);
				eventsByPoiName.put(detail.poiName(), events);
			}
		}
		


		poiNames.forEach(poiName -> {
			HashMap<String, Object> poiDetail = new HashMap<>();
			String poiDescription = output.stream()
										.filter(o -> o.poiName().equals(poiName))
										.findAny()
										.get()
										.poiDescription();


			poiDetail.put("name", poiName);
			poiDetail.put("description", poiDescription);
			poiDetail.put("events", eventsByPoiName.get(poiName));
			poiDetails.add(poiDetail);
		});

		TileDeepDetailsNested ret = new TileDeepDetailsNested(description, poiDetails);
		return ret;
	}

	public DetailedTile transformToDetailedTile(List<TileDeepDetails> detailsList) {
        if (detailsList.isEmpty()) {
            return new DetailedTile("", List.of());
        }

        String description = detailsList.get(0).description();

        Map<String, List<EventGet>> poiEventsMap = new LinkedHashMap<>();
        Map<String, String> poiDescriptionsMap = new LinkedHashMap<>();

        for (TileDeepDetails detail : detailsList) {
            poiDescriptionsMap.putIfAbsent(detail.poiName(), detail.poiDescription());
            poiEventsMap.computeIfAbsent(detail.poiName(), k -> new ArrayList<>())
                .add(new EventGet(detail.eventName(), detail.eventDescription()));
        }

        List<DetailedPointOfInterest> pois = poiDescriptionsMap.entrySet().stream()
            .map(entry -> new DetailedPointOfInterest(
                entry.getKey(),
                entry.getValue(),
                poiEventsMap.getOrDefault(entry.getKey(), List.of())))
            .toList();

        return new DetailedTile(description, pois);
    }
}
