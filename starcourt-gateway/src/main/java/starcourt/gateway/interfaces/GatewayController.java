package starcourt.gateway.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import starcourt.gateway.domain.CatalogClient;
import starcourt.gateway.domain.ReviewClient;
import starcourt.gateway.domain.StockClient;

import java.util.Map;

@RestController
public class GatewayController {

    private final CatalogClient catalogClient;
    private final StockClient stockClient;
    private final ReviewClient reviewClient;

    public GatewayController(CatalogClient catalogClient,
                             StockClient stockClient,
                             ReviewClient reviewClient) {
        this.catalogClient = catalogClient;
        this.stockClient = stockClient;
        this.reviewClient = reviewClient;
    }

    @GetMapping("{id}")
    public ResponseEntity index(@PathVariable Long id) {
        Map<String, Object> res = catalogClient.getCatalog(id);
        System.out.println(res);
        res = stockClient.getStock(id);
        System.out.println(res);
//        res = reviewClient.getReview(id);
//        System.out.println(res);

        return null;
    }
}
