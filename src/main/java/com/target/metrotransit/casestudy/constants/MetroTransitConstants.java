package com.target.metrotransit.casestudy.constants;

import java.util.HashMap;
import java.util.Map;

public class MetroTransitConstants
{

    public static final String GMT_5_TIME_ZONE = "GMT-5";
    public static final String GET_DEPARTURE_URL = "http://svc.metrotransit.org/NexTrip/{routeId}/{direction}/{stopValue}?format=json";
    public static final String GET_STOPS_URL = "http://svc.metrotransit.org/NexTrip/Stops/{routeId}/{direction}?format=json";
    public static final String GET_DIRECTIONS_URL = "http://svc.metrotransit.org/NexTrip/Directions/{direction}?format=json";
    public static final String GET_ROUTES_URL = "http://svc.metrotransit.org/NexTrip/Routes?format=json";
    public static final Map<String, String> DIRECTION_MAP = new HashMap<String, String>()
    {
        {
            put( "1", "South" );
            put( "2", "East" );
            put( "3", "West" );
            put( "4", "North" );
        }
    };
}
