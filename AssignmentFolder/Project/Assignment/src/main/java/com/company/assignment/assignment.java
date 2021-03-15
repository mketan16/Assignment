package com.company.assignment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class assignment extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(assignment.class);
	}
	public static void main(String args[]) {
		SpringApplication.run(assignment.class, args);
	}
	@Autowired
	private HttpSession session;
	@RequestMapping("")
    public String loginPage() {
		return "login.html";
    }
	@RequestMapping("/event")
	public String event() {
		if (session.isNew()) {
			return "redirect: login.html";
			
		}
		return "assignment/index.html";
	}
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logoutUser() {
		session.invalidate();
		return "login.html";
	}
}
