package com.labelapp.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LabelappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabelappApplication.class, args);
	}

}
