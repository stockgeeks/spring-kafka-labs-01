package io.resona.kafka.labs.quotes;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Repository
public class PureKafkaConsumer implements Runnable {

  public static final String STOCK_QUOTES_TOPIC = "stock-quotes";
  private Consumer<String, String> kafkaConsumer;
  @Getter
  private CountDownLatch countDownLatch = new CountDownLatch(1);
  @Getter
  private AtomicBoolean shouldRun = new AtomicBoolean(false);

  public PureKafkaConsumer() {
    kafkaConsumer = new KafkaConsumer<>(consumerConfiguration());
  }

  @PostConstruct
  public void initialize() {
    kafkaConsumer.subscribe(Collections.singletonList(STOCK_QUOTES_TOPIC));
    shouldRun.set(true);
    // we start the pooling consumer in new Thread so we don't block main thread.
    new Thread(this).start();
  }

  public void run() {
    log.info("PureKafkaConsumer.startConsumer");
    // we will start pooling for entries
    while (shouldRun.get()) {
      ConsumerRecords<String, String> records = this.getRecords();
      records.forEach(record -> {
        log.info("Got record: {}", record.value());
        countDownLatch.countDown();
      });
    }
  }

  public synchronized ConsumerRecords<String, String> getRecords() {
    return kafkaConsumer.poll(Duration.of(300, ChronoUnit.MILLIS));
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
  public synchronized void close() {
    shouldRun.set(false);
    kafkaConsumer.close();
  }
}
