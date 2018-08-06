package com.target.metrotransit.casestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController
{

    @RequestMapping( "/" )
    public String welcome( ModelMap mode )
    {
        return "welcome";
    }

}
