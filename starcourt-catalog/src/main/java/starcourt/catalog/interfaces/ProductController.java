package starcourt.catalog.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import starcourt.catalog.domain.Category;
import starcourt.catalog.domain.Product;
import starcourt.catalog.domain.ProductRepository;

import java.net.URI;

@RestController
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Product> index(@RequestParam(required = false) Category category, Pageable page) {
        return category != null
                ? repository.findAllByCategory(category, page)
                : repository.findAll(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> show(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Product product,
                                 UriComponentsBuilder builder,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body(result.getAllErrors());
        }
        if (repository.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Product entity = repository.save(product);
        URI uri = builder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @Validated @RequestBody Product product) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(product.getName());
                    entity.setDescription(product.getDescription());
                    entity.setCategory(product.getCategory());
                    entity.setPrice(product.getPrice());
                    entity.setManufacture(product.getManufacture());
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
