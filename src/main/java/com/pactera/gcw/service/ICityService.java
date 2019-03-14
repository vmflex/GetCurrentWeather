package com.pactera.gcw.service;

import java.util.List;

import com.pactera.gcw.dao.model.GcwCity;

public interface ICityService {

    /**
     * Get City object by code
     * @param code
     * @return GcwCity
     */
    GcwCity getGcwCity(String code);

    /**
     * Save a City object to DB
     * @param city GcwCity
     * @return true /false
     */
    boolean saveGcwCity(GcwCity city);

    /**
     * Save a city object by city code and city name
     * @param code
     * @param name
     * @return true/false
     */
    boolean saveGcwCity(String code, String name);

    /**
     * Delete a city from db by city code
     * @param code
     * @return true/false
     */
    boolean deleteGcwCity(String code);

    /**
     * Delete a city object by GcwCity 
     * @param city
     * @return true/false
     */
    boolean deleteGcwCity(GcwCity city);

    /**
     * Delete all cities from DB.
     * 
     * @return true/false
     */
    boolean deleteAllCities();

    /**
     * Get all available cities from DB.
     * @return
     */
    List<GcwCity> getAllGcwCities();

}
