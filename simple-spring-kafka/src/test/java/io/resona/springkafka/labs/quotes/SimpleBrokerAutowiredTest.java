package io.resona.springkafka.labs.quotes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TASK - Add JUnit 5 spring-kafka annotations 
@EmbeddedKafka(topics = SimpleConsumer.TOPIC)
@SpringJUnitConfig
public class SimpleBrokerAutowiredTest {

  // TASK - Autowire an EmbeddedKafkaBroker
  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  // Task - Create a testBroker method and test basic broker operation
  @Test
  public void testBroker() {
    assertThat(embeddedKafkaBroker.getZookeeperConnectionString()).startsWith("127");
    assertThat(embeddedKafkaBroker.getBrokerAddresses().length).isEqualTo(1);
    assertThat(embeddedKafkaBroker.getTopics().contains(SimpleConsumer.TOPIC)).isTrue();
    assertNotNull(embeddedKafkaBroker.getBrokersAsString());
  }
}
