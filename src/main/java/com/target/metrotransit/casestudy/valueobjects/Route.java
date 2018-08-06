package com.target.metrotransit.casestudy.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route
{
    @JsonProperty( "Description" )
    private String description;

    @JsonProperty( "Route" )
    private String route;

    public Route()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Route( String description,
        String route )
    {
        super();
        this.description = description;
        this.route = route;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getRoute()
    {
        return route;
    }

    public void setRoute( String route )
    {
        this.route = route;
    }

    @Override
    public String toString()
    {
        return "Route [description=" + description + ", route=" + route + "]";
    }

}
