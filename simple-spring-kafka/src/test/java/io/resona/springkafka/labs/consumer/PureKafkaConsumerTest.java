package io.resona.springkafka.labs.consumer;

import io.resona.kafka.labs.consumer.PureKafkaConsumer;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class PureKafkaConsumerTest {

  PureKafkaConsumer pureKafkaConsumer;

  @BeforeEach
  public void init() {
    pureKafkaConsumer = spy(PureKafkaConsumer.class);
  }

  @Test
  public void testConsumer() throws Exception {
    Map<TopicPartition, List<ConsumerRecord<String, String>>> records = new LinkedHashMap<>();
    ConsumerRecord<String, String> record = new ConsumerRecord<>(PureKafkaConsumer.STOCK_QUOTES_TOPIC, 1, 0, 0L, TimestampType.CREATE_TIME, 0L, 0, 0, "any", "my-value-in-topic");
    records.put(new TopicPartition(PureKafkaConsumer.STOCK_QUOTES_TOPIC, 0), Collections.singletonList(record));
    ConsumerRecords<String, String> consumerRecords = new ConsumerRecords<>(records);
    doReturn(consumerRecords).when(pureKafkaConsumer).getRecords();
    pureKafkaConsumer.initialize();
    assertEquals(0L, pureKafkaConsumer.getCountDownLatch().getCount());
    pureKafkaConsumer.getShouldRun().set(false);
    pureKafkaConsumer.close();
  }
}
