package com.target.metrotransit.casestudy.valueobjects;

public class MetroTransitResponse
{
    private boolean status;
    private String route;
    private String stop;
    private String direction;
    private String message;

    public MetroTransitResponse()
    {
        super();
    }

    public MetroTransitResponse( boolean status,
        String route,
        String stop,
        String direction,
        String message )
    {
        super();
        this.status = status;
        this.route = route;
        this.stop = stop;
        this.direction = direction;
        this.message = message;
    }

    public MetroTransitResponse( boolean status,
        String message )
    {
        super();
        this.status = status;
        this.message = message;
    }

    public String getRoute()
    {
        return route;
    }

    public void setRoute( String route )
    {
        this.route = route;
    }

    public String getStop()
    {
        return stop;
    }

    public void setStop( String stop )
    {
        this.stop = stop;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection( String direction )
    {
        this.direction = direction;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus( boolean status )
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

}
