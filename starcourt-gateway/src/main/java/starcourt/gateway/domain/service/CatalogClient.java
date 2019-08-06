package starcourt.gateway.domain.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;
import starcourt.gateway.domain.model.Product;

@RibbonClient("starcourt-catalog")
public class CatalogClient {

    private final RestTemplate restTemplate;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getProductFallback")
    public Product getProduct(Long id) {
        return restTemplate.getForObject("http://starcourt-catalog/{id}", Product.class, id);
    }

    public Product getProductFallback(Long id, Throwable throwable) {
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
