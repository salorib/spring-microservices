package com.springboot.restexample;

import java.util.Base64;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class RestexampleApplicationTests {

	//This test should fail
	@Test
	void contextLoads() {
		RestTemplate restTemplate =new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
		Assert.assertEquals("Hello World!", greet.getMessage());
	}
	
	// This test should pass
	@Test
	void testSecurity() {
		String plainCreds = "guest:guest123";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.getEncoder().encode(plainCreds.getBytes())));
		HttpEntity<String>request = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Greet> response = restTemplate.exchange("http://localhost:8080", HttpMethod.GET, request, Greet.class);
		Assert.assertEquals("Hello World!", response.getBody().getMessage());
	}
	

}
