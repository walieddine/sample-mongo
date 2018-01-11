package com.softparadigm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.softparadigm.domain.Product;
import com.softparadigm.domain.User;
import com.softparadigm.service.ProductService;
import com.softparadigm.service.UserService;

@SpringBootApplication
@ConfigurationProperties(prefix = "api")
public class SampleecommerceApplication {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SampleecommerceApplication.class, args);
	}

}
