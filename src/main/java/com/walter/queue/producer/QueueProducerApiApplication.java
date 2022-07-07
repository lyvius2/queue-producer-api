package com.walter.queue.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QueueProducerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueProducerApiApplication.class, args);
	}

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate(AmazonSQS amazonSQS) {
		return new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
	}
}
