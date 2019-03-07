package com.pactera.gcw.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.pactera.gcw.dao.model.WeatherModel;

@Service
public class GetWeatherService implements IGetWeatherService {

    private static final Logger logger = LoggerFactory.getLogger(GetWeatherService.class);
    @Value("${gcw.url}")
    private String gcwUrl;

    @Override
    public String getWeather(String id) {
        if (id == null || "".equals(id)) {
            return "incorrect parameter for weather code.";
        }

        InputStream is = null;
        StringBuffer buf = new StringBuffer();
        try {
            //URL url = new URL(this.gcwUrl + id + ".html");
            URL url = new URL(this.gcwUrl + id);
            is = url.openStream();
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            char[] cArr = new char[1024];
            int data = -1;
            while ((data = reader.read(cArr, 0, cArr.length)) != -1) {
                buf.append(cArr, 0, data);
            }

        } catch (MalformedURLException e) {
            logger.error("Fail to get Weather info from {}", this.gcwUrl, e);
        } catch (IOException e) {
            logger.error("Fail to get Weather info from {}", this.gcwUrl, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                    // close it silently
            }

        }
        String result = buf.toString();

        System.out.println(result);
        return result;
    }

    @Override
    public WeatherModel getWeatherBean(String id) {
        String result = this.getWeather(id);
        JSONObject jobj = JSON.parseObject(result);
        WeatherModel bean = new WeatherModel();
        if (jobj.containsKey("weatherinfo")) {
            JSONObject wobj = jobj.getJSONObject("weatherinfo");
            bean = wobj.toJavaObject(new TypeReference<WeatherModel>() {
            });
        }

        return bean;
    }
}
