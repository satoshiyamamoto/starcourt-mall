package starcourt.gateway.domain.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import starcourt.gateway.domain.model.Product.Review;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RibbonClient("starcourt-review")
public class ReviewClient {

    private final RestTemplate restTemplate;

    public ReviewClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Review> getReview(Long id) {
        ResponseEntity<Page<Review>> response = restTemplate.exchange("http://starcourt-review/?productId={id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Page<Review>>() {}, id);
        return Objects.requireNonNull(response.getBody()).getContent();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Page<T> {
        private List<T> content;
    }
}