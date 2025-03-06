package com.product.cosmeticProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CosmeticProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmeticProductApplication.class, args);
	}

}
