package com.pactera.gcw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetCurrentWeatherApplication {
    private static final Logger logger = LoggerFactory.getLogger(GetCurrentWeatherApplication.class);
	public static void main(String[] args) {
        logger.debug("gcw system start...");
		SpringApplication.run(GetCurrentWeatherApplication.class, args);
	}
}
