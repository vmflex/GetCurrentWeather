package com.pactera.gcw.service;

import com.pactera.gcw.dao.model.WeatherModel;

public interface IGetWeatherService {

    /**
     * Obtain Weather info using normal URL API.
     * @param id city code
     * @return weather info as json string
     */
    String getWeather(String id);

    /**
     * Obtain Weather info, and convert it to Weather bean.
     * @param id city code
     * @return Weather bean
     */
    WeatherModel getWeatherBean(String id);
}
