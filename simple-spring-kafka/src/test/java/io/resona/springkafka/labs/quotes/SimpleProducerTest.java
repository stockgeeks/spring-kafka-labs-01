package io.resona.springkafka.labs.quotes;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    // TASK - test the producer, create a test Consumer to validate the message


    // TASK - send the message
    simpleProducer.send(testMessage);

    // TASK - check it was received by test consumer.
    

  }

}

