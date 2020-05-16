package io.resona.springkafka.labs.quotes.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
public class QuoteEndpoint {

  @PostMapping
  public ResponseEntity<QuoteDTO> publishQuote(@RequestBody QuoteDTO quoteDTO) {
    return ResponseEntity.ok(quoteDTO);
  }
}
