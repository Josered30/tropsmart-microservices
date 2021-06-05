package com.tropsmart.configurationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigurationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationserviceApplication.class, args);
	}

}
