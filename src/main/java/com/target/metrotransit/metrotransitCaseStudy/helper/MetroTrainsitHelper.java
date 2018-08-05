package com.target.metrotransit.metrotransitCaseStudy.helper;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.metrotransit.metrotransitCaseStudy.constants.MetroTransitConstants;
import com.target.metrotransit.metrotransitCaseStudy.entity.NexTripDeparture;
import com.target.metrotransit.metrotransitCaseStudy.entity.Route;
import com.target.metrotransit.metrotransitCaseStudy.entity.TextValuePair;

@Component
public class MetroTrainsitHelper
{

    RestTemplate template = new RestTemplate();

    Logger log = LoggerFactory.getLogger( this.getClass() );

    /**
     * 
     * @param route
     * @return
     */
    public Route getRoute( String route )
    {
        log.debug( "Finding route id for : {}", route );

        ResponseEntity<Route[]> routes = template.getForEntity( MetroTransitConstants.GET_ROUTES_URL, Route[].class );

        if( routes.getBody() == null )
        {
            return null;
        }

        for( Route routeObj : routes.getBody() )
        {
            if( routeObj.getDescription().contains( route ) )
            {

                log.debug( "Route info :- Description : {}, rootId : {}.", routeObj.getDescription(), routeObj.getRoute() );
                return routeObj;
            }
        }
        return null;
    }

    /**
     * 
     * @param direction
     * @param routeId
     * @return
     */
    public boolean isValidDirection( String direction,
                                     String routeId )
    {

        log.debug( "isValidDirection with :- direction : {}, routeId : {}.", direction, routeId );
        boolean valid = Boolean.FALSE;
        ResponseEntity<TextValuePair[]> directions = template.getForEntity( MetroTransitConstants.GET_DIRECTIONS_URL,
                                                                            TextValuePair[].class,
                                                                            routeId );
        if( directions.getBody() == null )
        {
            return false;
        }

        for( TextValuePair textValuePair : directions.getBody() )
        {
            if( direction.equals( textValuePair.getValue() ) )
            {
                valid = Boolean.TRUE;
                log.debug( "Route info :- Text : {}, Value : {}.", textValuePair.getText(), textValuePair.getValue() );
                break;
            }
        }

        return valid;
    }

    /**
     * 
     * @param stop
     * @param direction
     * @param routeId
     * @return
     */
    public TextValuePair getStop( String stop,
                                  String direction,
                                  String routeId )
    {

        log.debug( "Get Stop Value for :- Stop : {}, direction : {}, RouteID : {}.", stop, direction, routeId );

        ResponseEntity<TextValuePair[]> stops = template.getForEntity( MetroTransitConstants.GET_STOPS_URL,
                                                                       TextValuePair[].class,
                                                                       routeId,
                                                                       direction );
        if( stops.getBody() == null )
        {
            return null;
        }

        for( TextValuePair textValuePair : stops.getBody() )
        {
            if( textValuePair.getText().contains( stop ) )
            {
                log.debug( "Found Stop info, stop : {}, stopValue : {}.", textValuePair.getText(), textValuePair.getValue() );
                return textValuePair;
            }
        }

        return null;
    }

    /**
     * 
     * @param direction
     * @param routeId
     * @param stopValue
     * @return
     */
    public NexTripDeparture getNextTripDetail( String direction,
                                               String routeId,
                                               String stopValue )
    {

        log.debug( "Get Next Trip detail for :- Direction : {}, RouteId : {}, StopValue : {}.", direction, routeId, stopValue );

        NexTripDeparture lNexTripDeparture = null;

        ResponseEntity<NexTripDeparture[]> departure = template.getForEntity( MetroTransitConstants.GET_DEPARTURE_URL,
                                                                              NexTripDeparture[].class,
                                                                              routeId,
                                                                              direction,
                                                                              stopValue );
        if( departure.getBody() == null )
        {
            return null;
        }

        for( NexTripDeparture nexTripDeparture : departure.getBody() )
        {
            lNexTripDeparture = nexTripDeparture;
            log.debug( "Next Trip Departure : DepartureText : {}, DepartureTime : {}.",
                       nexTripDeparture.getDepartureText(),
                       nexTripDeparture.getDepartureTime() );
            break;
        }
        return lNexTripDeparture;
    }

    /**
     * 
     * @param departure
     * @return
     * @throws ParseException
     */
    public long getWaitTimeInMinutes( NexTripDeparture departure ) throws ParseException
    {

        TimeZone.setDefault( TimeZone.getTimeZone( MetroTransitConstants.GMT_5_TIME_ZONE ) );
        Calendar cal = Calendar.getInstance( TimeZone.getDefault() );
        Date CurrentDate = cal.getTime();

        Date departureDateTime = getDepartureTime( departure.getDepartureTime() );

        log.debug( "departureDateTime : {}.", departureDateTime );

        long timeDiff = departureDateTime.getTime() - CurrentDate.getTime();

        return timeDiff / ( 60 * 1000 ) % 60;

    }

    private Date getDepartureTime( String departureTime ) throws ParseException
    {
        String dateString = departureTime.split( "-" )[0].substring( 6 );

        Date date = new Date( Long.parseLong( dateString ) );

        return date;

    }

    public String toJson( Object response )
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( response );
        }
        catch( JsonProcessingException e )
        {

        }
        return null;

    }

}
