package com.pactera.gcw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.gcw.dao.model.WeatherModel;
import com.pactera.gcw.service.IGetWeatherService;

@RestController
public class GetWeatherController {

    /**
     * 
     */
    @Autowired
    private IGetWeatherService service;

    @RequestMapping(value = "/weatherinfo/{code}", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String getWeatherInfo(@PathVariable("code") String code) {
        String weather = service.getWeather(code);
        return weather;
    }

    @RequestMapping(value = "/weatherinfo2/{code}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public WeatherModel getWeatherInfoAsBean(@PathVariable("code") String code) {
        WeatherModel weather = service.getWeatherBean(code);
        return weather;
    }
}
