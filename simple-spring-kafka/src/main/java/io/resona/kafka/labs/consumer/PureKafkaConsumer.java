package io.resona.kafka.labs.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class PureKafkaConsumer {

  private KafkaConsumer<String, String> kafkaConsumer;

  @PostConstruct
  public void initialize() {
    kafkaConsumer = new KafkaConsumer<>(consumerConfiguration());
    kafkaConsumer.subscribe(Collections.singletonList("stock-quotes"));
    startConsumer();
  }

  public void startConsumer() {
    log.info("PureKafkaConsumer.startConsumer");
    // we will start pooling for entries
    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.of(300, ChronoUnit.MILLIS));
      records.forEach(record -> log.info("Got record: {}", record.value()));
    }
  }

  private Map<String, Object> consumerConfiguration() {
    Map<String, Object> consumerConfigProperties = new HashMap<>();
    consumerConfigProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Collections.singletonList("localhost:9092"));
    consumerConfigProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "simple-consumer-group-id");
    consumerConfigProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    consumerConfigProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    consumerConfigProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return consumerConfigProperties;
  }

  @PreDestroy
  public void close() {
    kafkaConsumer.close();
  }
}
