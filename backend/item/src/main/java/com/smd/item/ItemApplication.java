package com.smd.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smd.*")
public class ItemApplication
{

	public static void main(String[] args) {
		SpringApplication.run(ItemApplication.class, args);
	}

}
