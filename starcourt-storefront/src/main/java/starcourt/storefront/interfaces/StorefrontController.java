package starcourt.storefront.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import starcourt.storefront.application.StorefrontService;
import starcourt.storefront.domain.model.Product;

@RestController
public class StorefrontController {

    private final StorefrontService application;

    public StorefrontController(StorefrontService application) {
        this.application = application;
    }

    @GetMapping("{id}")
    public ResponseEntity index(@PathVariable Long id) {
        Product product = application.getProduct(id);
        return ResponseEntity.ok(product);
    }
}
