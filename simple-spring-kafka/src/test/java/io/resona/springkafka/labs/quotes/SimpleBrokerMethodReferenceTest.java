package io.resona.springkafka.labs.quotes;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TASK - Add EmbeddedKafka annotation
@EmbeddedKafka
public class SimpleBrokerMethodReferenceTest {


  // Task - Add method to test passing in the broker reference
  @Test
  public void testPassedInBrokerReference(EmbeddedKafkaBroker embeddedKafkaBroker) {
    embeddedKafkaBroker.addTopics(SimpleConsumer.TOPIC);
    assertThat(embeddedKafkaBroker.getZookeeperConnectionString()).startsWith("127");
    assertThat(embeddedKafkaBroker.getBrokerAddresses().length).isEqualTo(1);
    assertThat(embeddedKafkaBroker.getTopics().contains(SimpleConsumer.TOPIC)).isTrue();
    assertNotNull(embeddedKafkaBroker.getBrokersAsString());
  }
}
