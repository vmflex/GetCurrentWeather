/**
 * 
 */
package com.pactera.gcw.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * This is to test the class for GetWeatherController
 * @author Jimmy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetWeatherControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(GetWeatherControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    @BeforeClass
    public static void setUp() throws Exception {
        logger.debug("Test GetWeatherController START.");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        logger.debug("Test GetWeatherController END.");
    }

    @Test
    public void testGetWeatherInfo() throws Exception {
        ResponseEntity<String> response = this.template.getForEntity("/weatherinfo/Sydney", String.class);
        String jsonStr = response.getBody();
        assertNotNull(jsonStr);
        // using alibaba fast json to parse weather information
        JSONObject jobj = JSON.parseObject(jsonStr);
        JSONObject jobj1 = jobj.getJSONObject("location");
        String name = jobj1.getString("name");
        assertEquals(name, "Sydney");
        JSONObject jobj2 = jobj.getJSONObject("current");
        String last_updated = jobj2.getString("last_updated");
        String temp_c = jobj2.getString("temp_c");
        JSONObject jobj3 = jobj2.getJSONObject("condition");
        String weather = jobj3.getString("text");

        logger.debug(
                "Current Weather Info for City {} :\r\nTemperature:{}\r\nWeather:{}\r\nLast Updated Time:{}",
                name, temp_c, weather, last_updated);
    }

}
