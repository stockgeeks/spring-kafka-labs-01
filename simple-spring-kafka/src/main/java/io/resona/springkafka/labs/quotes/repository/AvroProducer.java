package io.resona.springkafka.labs.quotes.repository;

import io.stockgeeks.avro.StockQuote;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvroProducer {
  private final KafkaTemplate<String, StockQuote> quoteProducer;

  public void publish(String key, StockQuote stockQuote) {
    quoteProducer.send(TOPIC, key, stockQuote);
  }
  public static final String TOPIC = "stock-quotes";
}
