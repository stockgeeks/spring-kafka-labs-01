package io.resona.kafka.labs.quotes;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class PureKafkaProducer {

  public final static String TOPIC = "stock-quotes";
  Producer<String, String> kafkaProducer;

  @PostConstruct
  public void initialize() {
    kafkaProducer = buildProducer();
  }

  Producer<String, String> buildProducer() {
    return new KafkaProducer<>(producerConfiguration());
  }

  public void send(String key, String value) {
    kafkaProducer.send(new ProducerRecord<>(TOPIC, key, value));
  }

  private Map<String, Object> producerConfiguration() {
    Map<String, Object> producerConfigProperties = new HashMap<>();
    producerConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Collections.singletonList("localhost:9092"));
    producerConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    producerConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return producerConfigProperties;
  }

  @PreDestroy
  public void close() {
    kafkaProducer.close();
  }
}
