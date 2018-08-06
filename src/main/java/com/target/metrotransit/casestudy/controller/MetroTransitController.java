package com.target.metrotransit.casestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.metrotransit.casestudy.constants.MetroTransitConstants;
import com.target.metrotransit.casestudy.exception.ResourseNotFoundException;
import com.target.metrotransit.casestudy.helper.MetroTransitHelper;
import com.target.metrotransit.casestudy.valueobjects.MetroTransitRequest;
import com.target.metrotransit.casestudy.valueobjects.MetroTransitResponse;
import com.target.metrotransit.casestudy.valueobjects.NexTripDeparture;
import com.target.metrotransit.casestudy.valueobjects.Route;
import com.target.metrotransit.casestudy.valueobjects.TextValuePair;

/**
 * 
 * REST Controller to handle the Target Metro Transit information Application request
 * 
 * @author Shivashankar.Kuradag
 *
 */
@RestController
public class MetroTransitController
{

    static Logger log = LoggerFactory.getLogger( MetroTransitController.class );

    MetroTransitHelper metroTrainsitHelper;

    @Autowired
    public void setMetroTrainsitHelper( MetroTransitHelper metroTrainsitHelper )
    {
        this.metroTrainsitHelper = metroTrainsitHelper;
    }

    /**
     * 
     * Method to get the Waiting Time For Requested Stop, Route and Direction
     * 
     * @param stop
     * @param route
     * @param direction
     * @return
     * @throws Exception 
     */
    @RequestMapping( path = "/getWatingTime", method = RequestMethod.POST )
    public String getWaitingTime( @RequestBody MetroTransitRequest request ) throws Exception
    {
        try
        {
            Route route = metroTrainsitHelper.getRoute( request.getRoute() );

            boolean valid = metroTrainsitHelper.isValidDirection( request.getDirection(), route.getRoute() );

            if( !valid )
            {
                throw new ResourseNotFoundException( "For the given BUS ROUTE, Invalid DIRECTION is selected" );
            }

            TextValuePair stopInfo = metroTrainsitHelper.getStop( request.getStop(), request.getDirection(), route.getRoute() );

            NexTripDeparture departure = metroTrainsitHelper.getNextTripDetail( request.getDirection(),
                                                                                route.getRoute(),
                                                                                stopInfo.getValue() );
            long waitingTime = metroTrainsitHelper.getWaitTimeInMinutes( departure );

            log.debug( "Waiting time : {} Min", waitingTime );

            return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.TRUE,
                                                                         route.getDescription(),
                                                                         stopInfo.getText(),
                                                                         MetroTransitConstants.DIRECTION_MAP.get( request.getDirection() ),
                                                                         waitingTime + " Minutes." ) );

        }
        catch( Exception exception )
        {
            log.error( "Error While Processing the Request for Get Witing time for Route : {}, Stop : {}, Direction {}. Exception : {}",
                       request.getRoute(),
                       request.getStop(),
                       MetroTransitConstants.DIRECTION_MAP.get( request.getDirection() ),
                       exception );
            throw exception;
        }
    }

}
