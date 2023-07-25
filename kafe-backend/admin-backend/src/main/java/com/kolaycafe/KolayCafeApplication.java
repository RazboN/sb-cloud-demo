package com.kolaycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KolayCafeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KolayCafeApplication.class, args);
	}
}
