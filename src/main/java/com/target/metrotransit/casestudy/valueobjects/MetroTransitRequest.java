package com.target.metrotransit.casestudy.valueobjects;

public class MetroTransitRequest
{

    private String stop;
    private String route;
    private String direction;

    public String getStop()
    {
        return stop;
    }

    public void setStop( String stop )
    {
        this.stop = stop;
    }

    @Override
    public String toString()
    {
        return "MetrotransitRequest [stop=" + stop + ", route=" + route + ", direction=" + direction + "]";
    }

    public String getRoute()
    {
        return route;
    }

    public void setRoute( String route )
    {
        this.route = route;
    }

    public String getDirection()
    {
        return direction;
    }

    public MetroTransitRequest( String stop,
        String route,
        String direction )
    {
        this.stop = stop;
        this.route = route;
        this.direction = direction;
    }

    public MetroTransitRequest()
    {

    }

    public void setDirection( String direction )
    {
        this.direction = direction;
    }

}
