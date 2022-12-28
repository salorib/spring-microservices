package com.springboot.hateoasexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HateoasexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HateoasexampleApplication.class, args);
	}

}

@RestController
class GreetingController{
	
	
	@RequestMapping("/")
	Greet greet() {
		return new Greet("Hello World!");
	}
	
	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greet> greeting(@RequestParam (value = "name", required = false, defaultValue ="HATEOAS") String name){
		Greet greet = new Greet("Hello "+ name);
		
		greet.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());
		
		return new ResponseEntity<Greet>(greet, HttpStatus.OK);
	}
	
	
}

class Greet extends RepresentationModel{
	
	private String message;

	public Greet() {}

	public Greet(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}