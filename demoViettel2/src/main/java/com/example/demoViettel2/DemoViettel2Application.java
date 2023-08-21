package com.example.demoViettel2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class DemoViettel2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoViettel2Application.class, args);
	}

}
