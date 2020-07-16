package io.resona.springkafka.labs.quotes.api;

import io.resona.springkafka.labs.quotes.repository.AvroProducer;
import io.stockgeeks.avro.Exchange;
import io.stockgeeks.avro.StockQuote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteEndpoint {

  private final AvroProducer avroProducer;

  @PostMapping
  public ResponseEntity<QuoteDTO> publishQuote(@RequestBody QuoteDTO quoteDTO) {
    StockQuote stockQuote = convert(quoteDTO);
    avroProducer.publish(quoteDTO.getSymbol(), stockQuote);
    return ResponseEntity.ok(quoteDTO);
  }

  private StockQuote convert(QuoteDTO quoteDTO) {
    return
      StockQuote.newBuilder()
      .setSymbol(quoteDTO.getSymbol())
      .setTradeValue(quoteDTO.getTradeValue())
      .setHigh(quoteDTO.getHigh())
      .setLow(quoteDTO.getLow())
      .setTradeTime(parseDateTime(quoteDTO.getTradeTime()))
      .setExchange(Exchange.valueOf(quoteDTO.getExchange()))
      .build();
  }

  private String parseDateTime(String isoDate) {
    Instant instantDate = Instant.parse(isoDate);
    return String.valueOf(instantDate.toEpochMilli());
  }
}
