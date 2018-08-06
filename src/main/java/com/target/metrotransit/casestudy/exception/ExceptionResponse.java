package com.target.metrotransit.casestudy.exception;

import java.util.Date;

public class ExceptionResponse extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 8317100611077798539L;

    private Date timeStamp;
    private String message;
    private String description;

    public ExceptionResponse( Date timeStamp,
        String message,
        String description )
    {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }

    public ExceptionResponse()
    {
        super();
    }

    public ExceptionResponse( String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace )
    {
        super( message, cause, enableSuppression, writableStackTrace );
    }

    public ExceptionResponse( String message,
        Throwable cause )
    {
        super( message, cause );
    }

    public ExceptionResponse( String message )
    {
        super( message );
    }

    public ExceptionResponse( Throwable cause )
    {
        super( cause );
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp( Date timeStamp )
    {
        this.timeStamp = timeStamp;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

}
