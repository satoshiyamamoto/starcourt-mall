package starcourt.gateway.domain.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;

import static starcourt.gateway.domain.model.Product.*;

@RibbonClient("starcourt-stock")
public class StockClient {

    private final RestTemplate restTemplate;

    public StockClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Stock getStock(Long id) {
        return restTemplate.getForObject("http://starcourt-stock/{id}", Stock.class, id);
    }
}
