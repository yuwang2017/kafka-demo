package com.graycat.kafka.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.graycat.kafka.kafkaproducer.ProducerService;

@RestController
public class ProducerController {

	
	@Autowired
	ProducerService producerService;
	
	@GetMapping("/")
	public String goHome() {
		return "<h1>Hello, this is Kafka producer<.h1>";
	}

	@GetMapping("/ping/{topic}")
	public String sendMessage(@PathVariable String topic) {
		producerService.ping(topic);
		return "<h2>Ping sent to " + topic + "</h2>";
	}
	
	@GetMapping("/student/{topic}")
	public String sendStudentInfo(@PathVariable String topic) {
		producerService.sendStudentData(topic);
		return "<h2>Student Data sent to " + topic + "</h2>";
	}
}
