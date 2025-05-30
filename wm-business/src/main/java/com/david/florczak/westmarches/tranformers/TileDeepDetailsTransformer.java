package com.david.florczak.westmarches.tranformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.david.florczak.westmarches.dtos.TileDeepDetails;

@Component
public class TileDeepDetailsTransformer {

	public Object transform(List<TileDeepDetails> output) {
		HashMap<String, Object> ret = new HashMap<>();
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
		
		ret.put("description", description);
		ret.put("pointsOfInterest", poiDetails);
		
		return ret;
	}

}
