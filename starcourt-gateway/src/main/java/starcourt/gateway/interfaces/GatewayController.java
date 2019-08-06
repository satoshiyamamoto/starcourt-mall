package starcourt.gateway.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import starcourt.gateway.application.StarcourtService;
import starcourt.gateway.domain.model.Product;

@RestController
public class GatewayController {

    private final StarcourtService application;

    public GatewayController(StarcourtService application) {
        this.application = application;
    }

    @GetMapping("{id}")
    public ResponseEntity index(@PathVariable Long id) {
        Product product = application.getProduct(id);
        return ResponseEntity.ok(product);
    }
}
