package io.resona.springkafka.labs.quotes;

// Task - Make sure to import JUnit test version 5 instead of version 4. i.e - should not import org.junit.Test
import org.junit.jupiter.api.Test;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TASK - Add annotations to do a SrpingBoot Integration test.
@SpringBootTest
@EmbeddedKafka(topics = {SimpleProducer.TOPIC}, partitions = 1)
@SpringJUnitConfig
public class SimpleProducerTest {

  // TASK - Add autowired EmbeddedKafkaBroker
  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  // TASK - Add autowired SimpleProducer
  @Autowired
  private SimpleProducer simpleProducer;

  @Test
  public void testProducer() throws Exception {
    final String testMessage = "My test message";
    // TASK - send the message
    simpleProducer.send(testMessage);

    // TASK - test the producer, create a test Consumer to validate the message
    Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafkaBroker);
    consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
    Consumer<String, String> consumer = cf.createConsumer();
    this.embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, SimpleProducer.TOPIC);
    ConsumerRecord<String, String> consumedRecord = KafkaTestUtils.getSingleRecord(consumer, SimpleProducer.TOPIC);

    assertEquals(testMessage, consumedRecord.value());
  }

}

