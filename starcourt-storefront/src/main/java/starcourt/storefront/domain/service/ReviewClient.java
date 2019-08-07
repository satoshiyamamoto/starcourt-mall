package starcourt.storefront.domain.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import starcourt.storefront.domain.model.Product.Review;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RibbonClient("starcourt-review")
public class ReviewClient {

    private final RestTemplate restTemplate;

    public ReviewClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getReviewFallback")
    public List<Review> getReview(Long id) {
        ResponseEntity<Page<Review>> response = restTemplate.exchange("http://starcourt-review/?productId={id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Page<Review>>() {}, id);
        return Objects.requireNonNull(response.getBody()).getContent();
    }

    public List<Review> getReviewFallback(Long id, Throwable throwable) {
        return Collections.emptyList();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Page<T> {
        private List<T> content;
    }
}
