package com.target.metrotransit.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class ResourseNotFoundException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = -9174716969763080332L;

    public ResourseNotFoundException( String message )
    {
        super( message );
    }

}
