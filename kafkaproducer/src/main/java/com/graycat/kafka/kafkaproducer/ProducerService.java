package com.graycat.kafka.kafkaproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graycat.kafka.kafkaproducer.model.Student;

@Component
public class ProducerService {
	private Properties props = new Properties();

	public ProducerService() {
		// Assign topicName to string variable
		// create instance for properties to access producer configs
		// Assign localhost id
		props.put("bootstrap.servers", "127.0.0.1:9092");

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", 16384);

		// Reduce the no of requests less than 0
		props.put("linger.ms", 1);

		// The buffer.memory controls the total amount of memory available to the
		// producer for buffering.
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	}

	public void ping(String topic) {
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 5; i++) {
			producer.send(new ProducerRecord<String, String>(topic, Integer.toString(i), Integer.toString(i)));
		}
		System.out.println("Message sent successfully");
		producer.close();
	}
	
	public void sendStudentData(String topic) {
		Student st = new Student(100, "John", "10", 17);
		ObjectMapper Obj = new ObjectMapper();
		 
        try {
 

            String jsonStr = Obj.writeValueAsString(st);
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
    	
    		producer.send(new ProducerRecord<String, String>(topic, "Student Info", jsonStr));
  
    		System.out.println("Message sent successfully");
    		producer.close();
            
            
        } catch (Exception e) {
        	
        }
		
	
	}
	
}
