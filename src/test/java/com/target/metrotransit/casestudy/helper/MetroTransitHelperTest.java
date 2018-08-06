package com.target.metrotransit.casestudy.helper;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.target.metrotransit.casestudy.constants.MetroTransitConstants;
import com.target.metrotransit.casestudy.exception.ResourseNotFoundException;
import com.target.metrotransit.casestudy.valueobjects.MetroTransitResponse;
import com.target.metrotransit.casestudy.valueobjects.NexTripDeparture;
import com.target.metrotransit.casestudy.valueobjects.Route;
import com.target.metrotransit.casestudy.valueobjects.TextValuePair;

import mockit.Expectations;
import mockit.Mocked;

public class MetroTransitHelperTest
{

    @Test
    public void getRouteTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        Route route1 = new Route( "disc1", "123" );
        Route route2 = new Route( "route", "125" );

        Route routes[] = new Route[2];
        routes[0] = route1;
        routes[1] = route2;

        ResponseEntity<Route[]> responseEntity = new ResponseEntity<Route[]>( routes, HttpStatus.OK );

        new Expectations()
        {
            {
                template.getForEntity( anyString, Route[].class );
                result = responseEntity;
            }
        };

        Route route = helper.getRoute( "route" );
        Assert.assertNotNull( route );
        Assert.assertEquals( route.getRoute(), "125" );
    }

    @Test( expectedExceptions = ResourseNotFoundException.class )
    public void getRouteWithEmptyRouteTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        Route route1 = new Route( "disc1", "123" );
        Route route2 = new Route( "disc2", "125" );

        Route routes[] = new Route[2];
        routes[0] = route1;
        routes[1] = route2;

        ResponseEntity<Route[]> responseEntity = new ResponseEntity<Route[]>( routes, HttpStatus.OK );

        new Expectations()
        {
            {
                template.getForEntity( anyString, Route[].class );
                result = responseEntity;
            }
        };

        Route route = helper.getRoute( "route" );
        Assert.assertNull( route );
    }

    @Test( expectedExceptions = ResourseNotFoundException.class )
    public void getRouteEmptyResponseTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        Route routes[] = new Route[0];
        ResponseEntity<Route[]> responseEntity = new ResponseEntity<Route[]>( routes, HttpStatus.OK );

        new Expectations()
        {
            {
                template.getForEntity( anyString, Route[].class );
                result = responseEntity;
            }
        };

        Route route = helper.getRoute( "route" );
        Assert.assertNull( route );
    }

    @Test
    public void isValidDirectionTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        TextValuePair textValuePair1 = new TextValuePair( "NORTHBOUND", "4" );
        TextValuePair textValuePair2 = new TextValuePair( "SOUTHBOUND", "1" );

        TextValuePair textValuePairs[] = new TextValuePair[2];
        textValuePairs[0] = textValuePair1;
        textValuePairs[1] = textValuePair2;

        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );

        new Expectations()
        {
            {

                template.getForEntity( MetroTransitConstants.GET_DIRECTIONS_URL, TextValuePair[].class, "125" );
                result = responseEntity;

            }
        };

        boolean isValid = helper.isValidDirection( "1", "125" );
        Assert.assertTrue( isValid );
    }

    @Test
    public void isValidDirectionFalseTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        TextValuePair textValuePair1 = new TextValuePair( "NORTHBOUND", "4" );
        TextValuePair textValuePair2 = new TextValuePair( "SOUTHBOUND", "1" );

        TextValuePair textValuePairs[] = new TextValuePair[2];
        textValuePairs[0] = textValuePair1;
        textValuePairs[1] = textValuePair2;

        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );

        new Expectations()
        {
            {

                template.getForEntity( MetroTransitConstants.GET_DIRECTIONS_URL, TextValuePair[].class, "125" );
                result = responseEntity;

            }
        };

        boolean isValid = helper.isValidDirection( "3", "125" );
        Assert.assertFalse( isValid );
    }

    @Test
    public void isValidDirectionWithEmptyResponse( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        TextValuePair textValuePairs[] = new TextValuePair[0];
        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );

        new Expectations()
        {
            {

                template.getForEntity( MetroTransitConstants.GET_DIRECTIONS_URL, TextValuePair[].class, "125" );
                result = responseEntity;

            }
        };
        boolean isValid = helper.isValidDirection( "3", "125" );
        Assert.assertFalse( isValid );
    }

    @Test
    public void getStopTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        TextValuePair textValuePair1 = new TextValuePair( "Starlite Transit Center", "STLI" );
        TextValuePair textValuePair2 = new TextValuePair( "63rd Ave  and Zane Ave", "63ZA" );

        TextValuePair textValuePairs[] = new TextValuePair[2];
        textValuePairs[0] = textValuePair1;
        textValuePairs[1] = textValuePair2;

        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );
        new Expectations()
        {
            {

                template.getForEntity( MetroTransitConstants.GET_STOPS_URL, TextValuePair[].class, "125", "1" );
                result = responseEntity;

            }
        };

        TextValuePair stopInfo = helper.getStop( "Transit Center", "1", "125" );
        Assert.assertNotNull( stopInfo );
        Assert.assertEquals( stopInfo.getValue(), "STLI" );
    }

    @Test( expectedExceptions = ResourseNotFoundException.class )
    public void getStopInvalidStopNameTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        TextValuePair textValuePair1 = new TextValuePair( "Starlite Transit Center", "STLI" );
        TextValuePair textValuePair2 = new TextValuePair( "63rd Ave  and Zane Ave", "63ZA" );

        TextValuePair textValuePairs[] = new TextValuePair[2];
        textValuePairs[0] = textValuePair1;
        textValuePairs[1] = textValuePair2;

        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );
        new Expectations()
        {
            {

                template.getForEntity( MetroTransitConstants.GET_STOPS_URL, TextValuePair[].class, "125", "1" );
                result = responseEntity;

            }
        };

        helper.getStop( "Unknown", "1", "125" );
    }

    @Test( expectedExceptions = ResourseNotFoundException.class )
    public void getStopEmptyNameTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        TextValuePair textValuePairs[] = new TextValuePair[0];
        ResponseEntity<TextValuePair[]> responseEntity = new ResponseEntity<TextValuePair[]>( textValuePairs, HttpStatus.OK );
        new Expectations()
        {
            {
                template.getForEntity( MetroTransitConstants.GET_STOPS_URL, TextValuePair[].class, "125", "1" );
                result = responseEntity;
            }
        };
        TextValuePair stopInfo = helper.getStop( "Unknown", "1", "125" );
        Assert.assertNull( stopInfo );
    }

    @Test
    public void getNextTripDetailTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        NexTripDeparture nexTripDeparture1 = new NexTripDeparture( "3:35", "/Date(1533328500000-0500)/" );
        NexTripDeparture nexTripDeparture2 = new NexTripDeparture( "4:05", "/Date(1533330300000-0500)/" );

        NexTripDeparture nexTrips[] = new NexTripDeparture[2];
        nexTrips[0] = nexTripDeparture1;
        nexTrips[1] = nexTripDeparture2;

        ResponseEntity<NexTripDeparture[]> responseEntity = new ResponseEntity<NexTripDeparture[]>( nexTrips, HttpStatus.OK );

        new Expectations()
        {
            {
                template.getForEntity( MetroTransitConstants.GET_DEPARTURE_URL, NexTripDeparture[].class, "123", "1", "STLI" );
                result = responseEntity;
            }
        };

        NexTripDeparture nexTripDeparture = helper.getNextTripDetail( "1", "123", "STLI" );
        Assert.assertNotNull( nexTripDeparture );
        Assert.assertEquals( nexTripDeparture.getDepartureTime(), "/Date(1533328500000-0500)/" );

    }

    @Test( expectedExceptions = ResourseNotFoundException.class )
    public void getNextTripDetailWithEmptyResponseTest( @Mocked RestTemplate template )
    {
        MetroTransitHelper helper = new MetroTransitHelper();

        NexTripDeparture nexTrips[] = null;

        ResponseEntity<NexTripDeparture[]> responseEntity = new ResponseEntity<NexTripDeparture[]>( nexTrips, HttpStatus.OK );

        new Expectations()
        {
            {
                template.getForEntity( MetroTransitConstants.GET_DEPARTURE_URL, NexTripDeparture[].class, "123", "1", "STLI" );
                result = responseEntity;
            }
        };

        NexTripDeparture nexTripDeparture = helper.getNextTripDetail( "1", "123", "STLI" );

    }

    @Test
    public void getWaitTimeInMinutesTest() throws ParseException
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        NexTripDeparture nexTripDeparture1 = new NexTripDeparture( "3:35", "/Date(1533328500000-0500)/" );
        long diffInTime = helper.getWaitTimeInMinutes( nexTripDeparture1 );
        Assert.assertNotNull( diffInTime );
    }

    @Test
    public void toJsonTest() throws JsonProcessingException
    {
        MetroTransitHelper helper = new MetroTransitHelper();
        MetroTransitResponse response = new MetroTransitResponse( Boolean.TRUE, "success" );
        String value = helper.toJson( response );
        Assert.assertNotNull( value );
    }

}
