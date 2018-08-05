package com.target.metrotransit.metrotransitCaseStudy.controller;

import org.testng.annotations.Test;

import com.target.metrotransit.metrotransitCaseStudy.entity.MetroTransitRequest;
import com.target.metrotransit.metrotransitCaseStudy.helper.MetroTrainsitHelper;

import mockit.Expectations;
import mockit.Mocked;

public class MetroTransitControllerTest
{

    @Test
    public void getWaitingTimeTest( @Mocked MetroTrainsitHelper helper )
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
                result = anyString;

                helper.getStop( anyString, anyString, anyString );
                result = anyString;

                helper.getNextTripDetail( anyString, anyString, anyString );
                result = anyString;

                helper.isValidDirection( anyString, anyString );
                result = true;

            }
        };

        String temp = controller.getWaitingTime( request );
    }

}
