package com.juno.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	//@RequestMapping(method = RequestMethod.GET,path = "/helloWorld")
	@GetMapping("/helloWorld1")
	public String helloWorld() {
		return "Hello World1!! Welcome to Springboot2";
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/helloWorld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Kuber", "Pujar", "Ranibennur");
	}
}
