package io.resona.springkafka.labs.quotes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SimpleProducer {

  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String message) {
    log.info("Sending message: {}", message);
    kafkaTemplate.send(TOPIC, null, message );
  }

  // TASK - Add constant for TOPIC
  public static final String TOPIC = "stock-quotes";
}
