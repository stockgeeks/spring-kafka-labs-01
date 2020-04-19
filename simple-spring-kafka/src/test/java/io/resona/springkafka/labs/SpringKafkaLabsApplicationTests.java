package io.resona.springkafka.labs;

import io.resona.springkafka.labs.quotes.SimpleConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SpringKafkaLabsApplicationTests {
	@MockBean
	private SimpleConsumer simpleConsumer;
	@Test
	void contextLoads() {
	}
}
