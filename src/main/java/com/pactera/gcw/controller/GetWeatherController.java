package com.pactera.gcw.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetWeatherController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return new Date().toString();
    }
}
