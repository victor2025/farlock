package com.victor2022.farlock;

import com.victor2022.farlock.locks.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FarlockSpringBootStarterTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarlockSpringBootStarterTestApplication.class, args);
	}

}
