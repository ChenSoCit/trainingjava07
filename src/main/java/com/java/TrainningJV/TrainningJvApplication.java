package com.java.TrainningJV;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.java.TrainningJV.mappers")
public class TrainningJvApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainningJvApplication.class, args);
	}

}
