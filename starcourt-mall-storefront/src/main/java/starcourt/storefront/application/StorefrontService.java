package starcourt.storefront.application;

import org.springframework.stereotype.Service;
import starcourt.storefront.domain.model.Product;
import starcourt.storefront.domain.service.GatewayClient;

import java.util.List;

@Service
public class StorefrontService {

    private final GatewayClient gatewayClient;

    public StorefrontService(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
    }

    public Product getProduct(Long id) {
        Product product = gatewayClient.getProduct(id);

        Product.Stock stock = gatewayClient.getStock(id);
        product.setStock(stock);

        List<Product.Review> reviews = gatewayClient.getReview(id);
        product.setReviews(reviews);

        return product;
    }
}
