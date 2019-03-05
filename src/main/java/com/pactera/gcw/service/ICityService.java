package com.pactera.gcw.service;

import java.util.List;

import com.pactera.gcw.dao.model.GcwCity;

public interface ICityService {

    GcwCity getGcwCity(String code);

    boolean saveGcwCity(GcwCity city);

    boolean saveGcwCity(String code, String name);

    boolean deleteGcwCity(String code);

    boolean deleteGcwCity(GcwCity city);

    List<GcwCity> getAllGcwCities();

}
