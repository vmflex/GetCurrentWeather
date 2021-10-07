/**
 * 
 */
package com.pactera.gcw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.gcw.dao.model.GcwCity;
import com.pactera.gcw.service.ICityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author dac
 *
 */
@RestController
public class CityController {

    @Autowired
    private ICityService service;

    /**
     * Get all available cities
     * @return
     */
    @ApiOperation(value = "Get All cities", notes = "Get all available cities from Database.")
    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public List<GcwCity> getAllCities() {
        return this.service.getAllGcwCities();
    }

    @ApiOperation(value = "Get a city by a city code", notes = "Get a city by a city code.")
    @ApiImplicitParam(name = "code", value = "City Code", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/city/{code}", method = RequestMethod.GET)
    public GcwCity getCityByCode(@PathVariable("code") String code) {
        return this.service.getGcwCity(code);
    }

    @ApiOperation(value = "Create a City", notes = "Create a city by city code and city name.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "City Code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "City Name", required = true, dataType = "String") })
    @RequestMapping(value = "/city/save", method = RequestMethod.POST)
    public GcwCity saveCity(String code, String name2) {
        GcwCity city = new GcwCity();
        city.setCode(code);
        city.setName(name2);
        this.service.saveGcwCity(city);
        return city;
    }

    @ApiOperation(value = "Delete a city.", notes = "Delete a city based on city code.")
    @ApiImplicitParam(name = "code", value = "City Code", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/city/del/{code}", method = RequestMethod.DELETE)
    public boolean deleteCity(@PathVariable("code") String code) {
        return this.service.deleteGcwCity(code);
    }

    @ApiOperation(value = "Delete All Cities...", notes = "Delete all cities.")
    @RequestMapping(value = "/city/del/all", method = RequestMethod.DELETE)
    public boolean deleteCity() {
        return this.service.deleteAllCities();
    }
    
    public boolean sayHello() {
        System.out.println("hello");
        return true;
    }

}
