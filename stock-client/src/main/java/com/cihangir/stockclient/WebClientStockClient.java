package com.cihangir.stockclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;



public class WebClientStockClient implements StockClient {

    Logger logger = LoggerFactory.getLogger(WebClientStockClient.class);
    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get()
                .uri("http://localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
                .doOnError(IOException.class, e -> logger.error(e.getMessage()));

    }


}
