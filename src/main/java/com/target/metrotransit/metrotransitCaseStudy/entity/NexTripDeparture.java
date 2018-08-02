package com.target.metrotransit.metrotransitCaseStudy.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NexTripDeparture {
	
	@JsonProperty("DepartureText")
	private String departureText;
	
	@JsonProperty("DepartureTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date departureTime;

	public NexTripDeparture() {
		super(); 
	}
	
	
	public String getDepartureText() {
		return departureText;
	}

	public void setDepartureText(String departureText) {
		this.departureText = departureText;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
		

}
