package org.test.samsung.fakestoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FakestoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakestoreApiApplication.class, args);
	}

}
