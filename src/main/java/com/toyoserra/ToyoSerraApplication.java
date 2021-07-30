package com.toyoserra;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToyoSerraApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR")); 
		SpringApplication.run(ToyoSerraApplication.class, args);
	}

}
