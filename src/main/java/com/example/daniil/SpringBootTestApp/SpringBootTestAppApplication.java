package com.example.daniil.SpringBootTestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootTestAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestAppApplication.class, args);
	}

	@Bean
	public void infoCurrencyRate() throws Exception {
		Request request = new Request();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = new RestTemplate();
		request.restTemplate(restTemplateBuilder);
		request.run(restTemplate);
	}
}