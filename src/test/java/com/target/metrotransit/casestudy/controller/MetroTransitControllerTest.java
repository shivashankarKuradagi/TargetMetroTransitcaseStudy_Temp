package com.target.metrotransit.casestudy.controller;

import org.springframework.boot.json.JsonParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.metrotransit.casestudy.helper.MetroTransitHelper;
import com.target.metrotransit.casestudy.valueobjects.MetroTransitRequest;
import com.target.metrotransit.casestudy.valueobjects.NexTripDeparture;
import com.target.metrotransit.casestudy.valueobjects.Route;
import com.target.metrotransit.casestudy.valueobjects.TextValuePair;

import mockit.Expectations;
import mockit.Mocked;

public class MetroTransitControllerTest
{

    @Test
    public void getWaitingTimeTest( @Mocked MetroTransitHelper helper ) throws Exception
    {
        MetroTransitController controller = new MetroTransitController();
        controller.setMetroTrainsitHelper( helper );
        MetroTransitRequest request = new MetroTransitRequest();
        request.setDirection( "1" );
        request.setRoute( "xyz" );
        request.setStop( "stopeName" );

        new Expectations()
        {
            {
                helper.getRoute( anyString );
                result = new Route( "desc", "123" );

                helper.isValidDirection( anyString, anyString );
                result = true;

                helper.getStop( anyString, anyString, anyString );
                result = new TextValuePair( "testjkcl sds", "1" );

                helper.getNextTripDetail( anyString, anyString, anyString );
                result = new NexTripDeparture( "3:35", "/Date(1533328500000-0500)/" );

                helper.toJson( any );
                result = "Valid Result";

            }
        };

        String temp = controller.getWaitingTime( request );
        Assert.assertNotNull( temp );

    }

    @Test( expectedExceptions = Exception.class )
    public void getWaitingTimeWithExceptionTest( @Mocked MetroTransitHelper helper ) throws Exception
    {
        MetroTransitController controller = new MetroTransitController();
        controller.setMetroTrainsitHelper( helper );
        MetroTransitRequest request = new MetroTransitRequest();
        request.setDirection( "1" );
        request.setRoute( "xyz" );
        request.setStop( "stopeName" );

        new Expectations()
        {
            {
                helper.getRoute( anyString );
                result = new Route( "desc", "123" );

                helper.isValidDirection( anyString, anyString );
                result = true;

                helper.getStop( anyString, anyString, anyString );
                result = new TextValuePair( "testjkcl sds", "1" );

                helper.getNextTripDetail( anyString, anyString, anyString );
                result = new NexTripDeparture( "3:35", "/Date(1533328500000-0500)/" );

                helper.toJson( any );
                result = new JsonParseException();

            }
        };
        controller.getWaitingTime( request );
    }
}
