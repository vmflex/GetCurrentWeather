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

/**
 * @author dac
 *
 */
@RestController
public class CityController {

    @Autowired
    private ICityService service;

    @RequestMapping("/city/list")
    public List<GcwCity> getAllCities() {
        return this.service.getAllGcwCities();
    }

    @RequestMapping("/city/{code}")
    public GcwCity getCityByCode(@PathVariable("code") String code) {
        return this.service.getGcwCity(code);
    }

    @RequestMapping(value = "/city/save", method = RequestMethod.POST)
    public GcwCity saveCity(String code, String name) {
        GcwCity city = new GcwCity();
        city.setCode(code);
        city.setName(name);
        this.service.saveGcwCity(city);
        return city;
    }

    @RequestMapping(value = "/city/del/{code}", method = RequestMethod.DELETE)
    public boolean deleteCity(@PathVariable("code") String code) {
        return this.service.deleteGcwCity(code);
    }

}
