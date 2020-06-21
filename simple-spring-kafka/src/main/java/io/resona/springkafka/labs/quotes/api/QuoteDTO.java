package io.resona.springkafka.labs.quotes.api;

import lombok.Data;

@Data
public class QuoteDTO {
  private String symbol;
  private String Exchange;
  private double high;
  private double low;
  private double tradeValue;
  private String tradeTime;
}
