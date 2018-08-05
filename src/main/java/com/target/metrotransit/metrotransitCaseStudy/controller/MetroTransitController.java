package com.target.metrotransit.metrotransitCaseStudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.metrotransit.metrotransitCaseStudy.constants.MetroTransitConstants;
import com.target.metrotransit.metrotransitCaseStudy.entity.MetroTransitRequest;
import com.target.metrotransit.metrotransitCaseStudy.entity.MetroTransitResponse;
import com.target.metrotransit.metrotransitCaseStudy.entity.NexTripDeparture;
import com.target.metrotransit.metrotransitCaseStudy.entity.Route;
import com.target.metrotransit.metrotransitCaseStudy.entity.TextValuePair;
import com.target.metrotransit.metrotransitCaseStudy.helper.MetroTrainsitHelper;

@RestController
public class MetroTransitController
{

    Logger log = LoggerFactory.getLogger( this.getClass() );

    MetroTrainsitHelper metroTrainsitHelper;

    @Autowired
    public void setMetroTrainsitHelper( MetroTrainsitHelper metroTrainsitHelper )
    {
        this.metroTrainsitHelper = metroTrainsitHelper;
    }

    /**
     * 
     * @param stop
     * @param route
     * @param direction
     * @return
     */
    @RequestMapping( path = "/getWatingTime", method = RequestMethod.POST )
    public String getWaitingTime( @RequestBody MetroTransitRequest request )
    {
        try
        {
            Route route = metroTrainsitHelper.getRoute( request.getRoute() );

            if( route == null )
            {
                return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.FALSE, "Invlid Route is selected." ) );
            }

            boolean valid = metroTrainsitHelper.isValidDirection( request.getDirection(), route.getRoute() );

            log.debug( "isValidDirection : {}.", valid );

            if( !valid )
            {
                return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.FALSE, "Invlid Direction is selected." ) );
            }

            TextValuePair stopInfo = metroTrainsitHelper.getStop( request.getStop(), request.getDirection(), route.getRoute() );

            if( stopInfo == null )
            {
                return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.FALSE, "Invlid Stop is selected." ) );
            }

            NexTripDeparture departure = metroTrainsitHelper.getNextTripDetail( request.getDirection(),
                                                                                route.getRoute(),
                                                                                stopInfo.getValue() );

            log.debug( "NexTripDeparture : {}.", departure );

            if( departure == null )
            {
                return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.FALSE,
                                                                             " At this time, Buses are not avalable for the selected Route and Stop." ) );
            }

            long waitingTime = metroTrainsitHelper.getWaitTimeInMinutes( departure );
            log.debug( "Waiting time : {} Min" + waitingTime );

            return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.TRUE,
                                                                         "BUS ROUTE : "
                                                                             + route.getDescription()
                                                                             + "<br> BUS STOP NAME : "
                                                                             + stopInfo.getText()
                                                                             + "<br> DIRECTION : "
                                                                             + MetroTransitConstants.DIRECTION_MAP.get( request.getDirection() )
                                                                             + "<br><br>  Waiting Time : "
                                                                             + waitingTime
                                                                             + " Minutes." ) );

        }
        catch( Exception e )
        {
            return metroTrainsitHelper.toJson( new MetroTransitResponse( Boolean.FALSE, "Error While fetching Waiting time." ) );
        }
    }

}
