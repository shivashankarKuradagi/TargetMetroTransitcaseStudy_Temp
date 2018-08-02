package com.target.metrotransit.metrotransitCaseStudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.target.metrotransit.metrotransitCaseStudy.entity.NexTripDeparture;
import com.target.metrotransit.metrotransitCaseStudy.entity.Route;
import com.target.metrotransit.metrotransitCaseStudy.entity.TextValuePair;

@RestController
public class MetrotransitController {

	RestTemplate template = new RestTemplate();

	@SuppressWarnings("null")
	@GetMapping("/getWatingTime")
	public NexTripDeparture[] getWaitingTime() {

		int routeId = 0;
		boolean isValidDirection = Boolean.FALSE;
		String directionId = "1";
		String stop = "Target North Campus Building F";
		String stopValue = null;

		ResponseEntity<Route[]> routes = template.getForEntity("http://svc.metrotransit.org/NexTrip/Routes?format=json",
				Route[].class);

		for (Route route : routes.getBody()) {
			if ("765 - Express - Target - Hwy 252 and 73rd Av P&R - Mpls".contains(route.getDescription())) {
				routeId = route.getRoute();
				break;
			}
		}

		ResponseEntity<TextValuePair[]> direction = template.getForEntity(
				"http://svc.metrotransit.org/NexTrip/Directions/" + routeId + "?format=json", TextValuePair[].class);

		if (direction != null) {

			for (TextValuePair textValuePair : direction.getBody()) {
				if (directionId.equals(textValuePair.getValue())) {
					isValidDirection = Boolean.TRUE;
					break;
				}
			}

		}

		ResponseEntity<TextValuePair[]> stops = template.getForEntity(
				"http://svc.metrotransit.org/NexTrip/Stops/" + routeId + "/" + directionId + "?format=json",
				TextValuePair[].class);

		if (stops != null) {
			for (TextValuePair textValuePair : stops.getBody()) {
				if (stop.contains(textValuePair.getText())) {
					stopValue = textValuePair.getValue();
				}
			}
		}

		ResponseEntity<NexTripDeparture[]> departure = template
				.getForEntity("http://svc.metrotransit.org/NexTrip/" + routeId + "/" + directionId + "/"
						+ stopValue + "?format=json", NexTripDeparture[].class);

		if (departure != null) {
			for (NexTripDeparture nexTripDeparture : departure.getBody()) {
				System.out.println(nexTripDeparture.getDepartureTime());
			}
		}

		return departure.getBody();
	}

}
