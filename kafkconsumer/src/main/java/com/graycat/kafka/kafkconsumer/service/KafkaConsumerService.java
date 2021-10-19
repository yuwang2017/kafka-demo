package com.graycat.kafka.kafkconsumer.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@Component
public class KafkaConsumerService {

	KafkaConsumer<String, String> consumer = null;
	boolean runProcess = true;
	Properties props = new Properties();

	public KafkaConsumerService() throws ClassNotFoundException {

		// Kafka consumer configuration settings
		Thread.currentThread().setContextClassLoader(null);

		props.put("bootstrap.servers", "127.0.0.1:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		//props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

		// print the topic name
		System.out.println("Subscribed to topic echo");
		runProcess = true;
	}

	@Async
	public void pollMessage(String topic) {
		this.consumer = new KafkaConsumer<String, String>(props);
		List<String> topics = new ArrayList<>();
		topics.add("echo");
		topics.add(topic);
		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(topics);
		this.runProcess = true;
		final int giveUp = 1000;
		int noRecordsCount = 0;

		while (runProcess) {
			final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
			if (consumerRecords.count() == 0) {
				noRecordsCount++;
				if (noRecordsCount > giveUp)
					break;
				else
					continue;
			}
			consumerRecords.forEach(record -> {
				
				System.out.printf("Consumer Record:(%s, %s, %s, %d, %d)\n", record.topic(), record.key(), record.value(),
						record.partition(), record.offset());
			});

			consumer.commitAsync();
		}
		consumer.close();
		System.out.println("DONE");
	}

	public void stop() {
		this.runProcess = false;
	}

}
