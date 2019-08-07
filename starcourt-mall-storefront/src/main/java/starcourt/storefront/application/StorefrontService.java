package starcourt.storefront.application;

import org.springframework.stereotype.Service;
import starcourt.storefront.domain.model.Product;
import starcourt.storefront.domain.service.CatalogClient;
import starcourt.storefront.domain.service.ReviewClient;
import starcourt.storefront.domain.service.StockClient;

import java.util.List;

@Service
public class StorefrontService {

    private final CatalogClient catalogClient;
    private final StockClient stockClient;
    private final ReviewClient reviewClient;

    public StorefrontService(CatalogClient catalogClient,
                             StockClient stockClient,
                             ReviewClient reviewClient) {
        this.catalogClient = catalogClient;
        this.stockClient = stockClient;
        this.reviewClient = reviewClient;
    }

    public Product getProduct(Long id) {
        Product product = catalogClient.getProduct(id);

        Product.Stock stock = stockClient.getStock(id);
        product.setStock(stock);

        List<Product.Review> reviews = reviewClient.getReview(id);
        product.setReviews(reviews);

        return product;
    }
}
