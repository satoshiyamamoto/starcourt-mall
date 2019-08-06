package starcourt.gateway.domain.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;
import starcourt.gateway.domain.model.Product;

@RibbonClient("starcourt-catalog")
public class CatalogClient {

    private final RestTemplate restTemplate;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProduct(Long id) {
        return restTemplate.getForObject("http://starcourt-catalog/{id}", Product.class, id);
    }
}
