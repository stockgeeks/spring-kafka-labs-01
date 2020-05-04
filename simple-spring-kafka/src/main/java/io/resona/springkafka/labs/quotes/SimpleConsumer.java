package io.resona.springkafka.labs.quotes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Repository
public class SimpleConsumer {

  // TASK - Add constants for TOPIC and LISTENER_CONTAINER_ID  
  public static final String TOPIC = "stock-quotes";
  public static final String LISTENER_CONTAINER_ID = "simple-consumer-id";

  // TASK - Add a CountDownLatch and initialize to 1
  private CountDownLatch countDownLatch = new CountDownLatch(1);

  // TASK - ADD A Spring Kafka consumer using @KafkaListener annotation
  @KafkaListener(id = LISTENER_CONTAINER_ID, topics = TOPIC)
  public void onMessage(String message) {
    countDownLatch.countDown();
    log.info("Got message: {}", message);
  }

  // TASK - Add a CountDownLatch getter
  public CountDownLatch getCountDownLatch() {
    return this.countDownLatch;
  }
}
