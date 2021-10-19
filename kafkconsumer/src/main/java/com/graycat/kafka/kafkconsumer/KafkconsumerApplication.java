package com.graycat.kafka.kafkconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkconsumerApplication.class, args);
	}

}
