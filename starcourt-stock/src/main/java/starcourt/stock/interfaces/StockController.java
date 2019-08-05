package starcourt.stock.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import starcourt.stock.domain.Stock;
import starcourt.stock.domain.StockDetail;
import starcourt.stock.domain.StockRepository;

import java.net.URI;
import java.util.List;

@RestController
public class StockController {

    private final StockRepository repository;

    public StockController(StockRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity notFound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<StockDetail> show(@PathVariable Long productId,
                                            Pageable page) {
        List<Stock> stocks = repository.findAllByProductId(productId, page).getContent();
        StockDetail details = StockDetail.of(stocks);

        return ResponseEntity.ok(details);
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Stock stock,
                                 UriComponentsBuilder builder,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body(result.getAllErrors());
        }

        Stock entity = repository.save(stock);
        URI uri = builder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @Validated @RequestBody Stock stock) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setReceiving(stock.getReceiving());
                    entity.setShipping(stock.getShipping());
                    entity.setDescription(stock.getDescription());
                    repository.save(entity);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
