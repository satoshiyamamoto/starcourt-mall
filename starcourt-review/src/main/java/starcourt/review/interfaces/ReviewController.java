package starcourt.review.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import starcourt.review.domain.Review;
import starcourt.review.domain.ReviewRepository;

import java.net.URI;

@RestController
public class ReviewController {

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Review> index(@RequestParam(required = false) Long productId,
                              @RequestParam(required = false) String username,
                              Pageable page) {
        if (productId != null) {
            return repository.findAllByProductId(productId, page);
        }
        if (username != null) {
            return repository.findAllByUsername(username, page);
        }
        return repository.findAll(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> show(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Review review,
                                 UriComponentsBuilder builder,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body(result.getAllErrors());
        }

        Review entity = repository.save(review);
        URI uri = builder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @Validated @RequestBody Review review) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setTitle(review.getTitle());
                    entity.setComment(review.getComment());
                    entity.setRating(review.getRating());
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
