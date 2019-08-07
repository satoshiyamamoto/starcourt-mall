package starcourt.storefront.domain.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import starcourt.storefront.domain.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RibbonClient("starcourt-mall-gateway")
public class GatewayClient {

    private final RestTemplate restTemplate;

    public GatewayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getProductFallback")
    public Product getProduct(Long id) {
        return restTemplate.getForObject("http://starcourt-mall-gateway/catalog/{id}", Product.class, id);
    }

    @HystrixCommand(fallbackMethod = "getStockFallback")
    public Product.Stock getStock(Long id) {
        return restTemplate.getForObject("http://starcourt-mall-gateway/stock/{id}", Product.Stock.class, id);
    }

    @HystrixCommand(fallbackMethod = "getReviewFallback")
    public List<Product.Review> getReview(Long id) {
        ResponseEntity<Page<Product.Review>> response = restTemplate.exchange("http://starcourt-mall-gateway/review/?productId={id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Page<Product.Review>>() {}, id);
        return Objects.requireNonNull(response.getBody()).getContent();
    }

    public Product getProductFallback(Long id, Throwable throwable) {
        Product product = new Product();
        product.setId(id);
        return product;
    }

    public Product.Stock getStockFallback(Long id, Throwable throwable) {
        return new Product.Stock();
    }

    public List<Product.Review> getReviewFallback(Long id, Throwable throwable) {
        return Collections.emptyList();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Page<T> {
        private List<T> content;
    }
}
