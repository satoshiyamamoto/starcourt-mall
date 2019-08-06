package starcourt.gateway.domain.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;

import static starcourt.gateway.domain.model.Product.Stock;

@RibbonClient("starcourt-stock")
public class StockClient {

    private final RestTemplate restTemplate;

    public StockClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getStockFallback")
    public Stock getStock(Long id) {
        return restTemplate.getForObject("http://starcourt-stock/{id}", Stock.class, id);
    }

    public Stock getStockFallback(Long id, Throwable throwable) {
        return new Stock();
    }
}
