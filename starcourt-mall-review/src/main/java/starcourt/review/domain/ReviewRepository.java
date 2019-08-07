package starcourt.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByUsername(String username, Pageable page);

    Page<Review> findAllByProductId(Long id, Pageable page);
}
