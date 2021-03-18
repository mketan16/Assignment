package com.company.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	/*@RequestMapping("")
    public String login() {
		return "login.html";
    }*/
	
	@RequestMapping("/employee")
	public String event() {
		return "/employee/index.html";
	}

}
