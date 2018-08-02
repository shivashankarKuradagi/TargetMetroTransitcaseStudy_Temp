package com.target.metrotransit.metrotransitCaseStudy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("ProviderID")
	private int providerID;
	
	@JsonProperty("Route")
	private int route;
	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public int getRoute() {
		return route;
	}
	public void setRoute(int route) {
		this.route = route;
	}
	
	
	
}
