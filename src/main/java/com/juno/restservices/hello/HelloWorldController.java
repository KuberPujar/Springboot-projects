package com.juno.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource msgSource;

	// @RequestMapping(method = RequestMethod.GET,path = "/helloWorld")
	@GetMapping("/helloWorld1")
	public String helloWorld() {
		return "Hello World1!! Welcome to Springboot2";
	}

	@RequestMapping(method = RequestMethod.GET,path = "/helloWorld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Kuber", "Pujar", "Ranibennur");
	}

	@GetMapping("/helloWorld-int")
	public String getMessagesInI18Int(@RequestHeader(value = "Accept-Language",required = false) String locale) {
		return msgSource.getMessage("label.hello",null, new Locale(locale));
	}
	
	@GetMapping("/helloWorld-int2")
	public String getMessagesInI18Int2() {
		return msgSource.getMessage("label.hello",null,LocaleContextHolder.getLocale());
	}
}
