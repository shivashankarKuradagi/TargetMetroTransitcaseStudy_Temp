package com.target.metrotransit.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( "com.target.metrotransit.casestudy.*" )
public class MetrotransitCaseStudyApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run( MetrotransitCaseStudyApplication.class, args );
    }
}
