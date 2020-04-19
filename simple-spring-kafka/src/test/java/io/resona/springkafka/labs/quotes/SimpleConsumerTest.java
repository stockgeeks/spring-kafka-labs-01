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
@EmbeddedKafka(topics = {SimpleConsumer.TOPIC}, partitions = 1)
@SpringJUnitConfig
public class SimpleConsumerTest {

  // TASK - Add autowired EmbeddedKafkaBroker
  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  // TASK - Add autowired SimpleConsumer
  @Autowired
  private SimpleConsumer simpleConsumer;

  // TASK - Add autowired KafkaListenerEndpointRegistry
  @Autowired
  private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;


  @Test
  public void testConsumer() throws Exception {
    // Task - Add a MessageListenerContainer and use Spring Kafka ContainerTestUtils to wait for the consumer to be ready.
    // we make sure the consumer is running so we wait for assignment using spring Test utilities.
    MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(SimpleConsumer.LISTENER_CONTAINER_ID);
    ContainerTestUtils.waitForAssignment(listenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());

    // Task - Use the Spring Kafka utils to create a producer and send a message
    // we need a test producer to send a message to topic.
    Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
    producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);
    // we send a messaage
    producer.send(new ProducerRecord<>(SimpleConsumer.TOPIC, "my-key", "My test message"));
    producer.close();

    // Task - Check if the message was processed by our SimpleConsumer
    simpleConsumer.getCountDownLatch().await(3000, TimeUnit.MILLISECONDS);
    assertEquals(0, simpleConsumer.getCountDownLatch().getCount());
  }

}

