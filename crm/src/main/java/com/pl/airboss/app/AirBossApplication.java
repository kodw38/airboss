package com.pl.airboss.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
public class AirBossApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(AirBossApplication.class, args);
	}

}
