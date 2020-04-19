package io.resona.springkafka.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringKafkaLabsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaLabsApplication.class, args);
	}
}
