package io.resona.kafka.labs.quotes;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class PureKafkaProducerTest {

  PureKafkaProducer pureKafkaProducer;
  MockProducer<String, String> mockProducer;

  @BeforeEach
  public void init() {
    pureKafkaProducer = spy(PureKafkaProducer.class);
    mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());
  }

  @Test
  public void testProducer() {
    doReturn(mockProducer).when(pureKafkaProducer).buildProducer();
    pureKafkaProducer.initialize();
    pureKafkaProducer.send("my-key", "my-value");
    assertTrue(mockProducer.flushed());
  }

}
