package io.resona.springkafka.labs.quotes.repository;

import io.stockgeeks.avro.StockQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Repository
public class AvroConsumer {

  public static final String TOPIC = "stock-quotes";
  public static final String LISTENER_CONTAINER_ID = "avro-consumer-id";
  public static final String GROUP_ID = "avro-consumer-group-id";

  private CountDownLatch countDownLatch = new CountDownLatch(1);

  @KafkaListener(id = LISTENER_CONTAINER_ID, topics = TOPIC, idIsGroup = false, groupId = GROUP_ID)
  public void onMessage(StockQuote stockQuote) {
    countDownLatch.countDown();
    log.info("Got message: {}", stockQuote);
  }

  public CountDownLatch getCountDownLatch() {
    return this.countDownLatch;
  }
}
