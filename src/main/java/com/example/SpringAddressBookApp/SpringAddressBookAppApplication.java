package com.example.SpringAddressBookApp;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class SpringAddressBookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAddressBookAppApplication.class, args);
	}

}
