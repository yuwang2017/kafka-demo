package com.graycat.kafka.kafkconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.graycat.kafka.kafkconsumer.service.KafkaConsumerService;

@RestController
public class ConsumerController {
	
	@Autowired
	KafkaConsumerService kafkaService;
	
	@GetMapping("/")
	public String goHome() {
		return "<h1>Hello, this is Kafka consumer</h1>";
	}
	
	@GetMapping("/start/{topic}")
	public String start(@PathVariable String topic) {
		this.kafkaService.pollMessage(topic);
		return "<h1>Hello, this is Kafka consumer started</h1>";
	}
	
	@GetMapping("/stop")
	public String stop() {
		this.kafkaService.stop();
		return "<h1>Hello, this is Kafka consumer stopped</h1>";
	}
}
