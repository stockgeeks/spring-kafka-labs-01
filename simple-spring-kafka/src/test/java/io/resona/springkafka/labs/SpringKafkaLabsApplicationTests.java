package io.resona.springkafka.labs;

import io.resona.springkafka.labs.quotes.SimpleProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SpringKafkaLabsApplicationTests {
	@MockBean
	private SimpleProducer simpleConsumer;
	@Test
	void contextLoads() {
	}
}
