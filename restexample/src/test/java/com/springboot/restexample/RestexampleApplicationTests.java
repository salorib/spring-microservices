package com.springboot.restexample;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class RestexampleApplicationTests {

	@Test
	void contextLoads() {
		RestTemplate restTemplate =new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
		Assert.assertEquals("Hello World!", greet.getMessage());
	}

}
