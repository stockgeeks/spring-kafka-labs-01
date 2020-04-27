package io.resona.springkafka.labs.quotes;

import io.resona.kafka.labs.quotes.PureKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.record.TimestampType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class PureKafkaConsumerTest {

  PureKafkaConsumer pureKafkaConsumer;

  @BeforeEach
  public void init() {
    // we use spy so we can partially mock the tested class but still execute the methods we don't want to mock
    pureKafkaConsumer = spy(PureKafkaConsumer.class);
  }

  @Test
  public void testConsumer() throws Exception {

    // mock the pooling for the record as we don't want to do an Integration test in this case
    Map<TopicPartition, List<ConsumerRecord<String, String>>> records = new LinkedHashMap<>();
    ConsumerRecord<String, String> record = new ConsumerRecord<>(PureKafkaConsumer.STOCK_QUOTES_TOPIC, 1, 0, 0L, TimestampType.CREATE_TIME, 0L, 0, 0, "any", "my-value-in-topic");
    records.put(new TopicPartition(PureKafkaConsumer.STOCK_QUOTES_TOPIC, 0), Collections.singletonList(record));
    ConsumerRecords<String, String> consumerRecords = new ConsumerRecords<>(records);
    // return the records
    doReturn(consumerRecords).when(pureKafkaConsumer).getRecords();

    // initialize the consumer
    pureKafkaConsumer.initialize();
    pureKafkaConsumer.getCountDownLatch().await(3_000, TimeUnit.MILLISECONDS);
    assertEquals(0L, pureKafkaConsumer.getCountDownLatch().getCount());

    // shut down the client
    pureKafkaConsumer.getShouldRun().set(false);
    pureKafkaConsumer.close();
  }
}
