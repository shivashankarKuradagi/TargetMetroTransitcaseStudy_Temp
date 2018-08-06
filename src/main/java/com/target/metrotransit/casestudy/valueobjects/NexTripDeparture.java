package com.target.metrotransit.casestudy.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NexTripDeparture
{

    @JsonProperty( "DepartureText" )
    private String departureText;

    @JsonProperty( "DepartureTime" )
    private String departureTime;

    public NexTripDeparture()
    {
        super();
    }

    public String getDepartureText()
    {
        return departureText;
    }

    public void setDepartureText( String departureText )
    {
        this.departureText = departureText;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime( String departureTime )
    {
        this.departureTime = departureTime;
    }

    public NexTripDeparture( String departureText,
        String departureTime )
    {
        super();
        this.departureText = departureText;
        this.departureTime = departureTime;
    }

}
