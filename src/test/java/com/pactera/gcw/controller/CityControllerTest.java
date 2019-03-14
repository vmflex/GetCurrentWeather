/**
 * 
 */
package com.pactera.gcw.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONArray;
import com.pactera.gcw.dao.model.GcwCity;

/**
 * @author Jimmy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CityControllerTest.class);
    @Autowired
    private TestRestTemplate template;

    @BeforeClass
    public static void setUp() throws Exception {
        logger.debug("Test CityController START.");
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        logger.debug("Test CityController END.");
    }
    
    @Test
    public void testADeleteAll() throws Exception {
        this.template.delete("/city/del/all");
        logger.debug("All Cities has been deleted successfully.");
    }

    @Test
    public void testBSaveCity() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("code", "Shanghai");
        map.add("name", "Shanghai");
        ResponseEntity<GcwCity> response = this.template.postForEntity("/city/save", map, GcwCity.class);
        GcwCity result = response.getBody();
        assertEquals("Shanghai", result.getCode());
    }

    @Test
    public void testCListCities() throws Exception {
        ResponseEntity<JSONArray> response = this.template.getForEntity("/city/list", JSONArray.class);
        JSONArray result = response.getBody();
        assertTrue(result.size() > 0);
        logger.debug("Get All Cities:{}", result.toString());
    }
}
