package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

//Controller
@RestController
@Log4j2
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	//Simple method
	//URI - /helloworld
	//GET 
	//@RequestMapping(method = RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1";
	}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Daniela", "Lira", "Caracas");
	}
	@GetMapping("/helloworld-int")
	public String getMessagesInI18NFormat (@RequestHeader(name="Accept-Language",required=false) String locale) {
		
		log.info("Entrando en INT18Format");
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/helloworld-int2")
	public String getMessagesInI18NFormat2 () {
		
		log.info("Entrando en INT18Format2");
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
