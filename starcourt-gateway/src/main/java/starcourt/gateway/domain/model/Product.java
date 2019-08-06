package starcourt.gateway.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Product {

    private Long id;

    private String name;

    private String description;

    private Category category;

    private BigDecimal price;

    private String manufacture;

    private Stock stock;

    private List<Review> reviews;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public enum Category {
        FASHION, ICE_CREAM,
        ;
    }

    @Data
    public static class Stock {

        private Long quantity = 0L;

        private Status status = Status.UNAVAILABLE;
    }

    public enum Status {
        AVAILABLE, UNAVAILABLE,
        ;
    }

    @Data
    public static class Review {

        private Long id;

        private Long productId;

        private String username;

        private String title;

        private String comment;

        private Integer rating;

        private ZonedDateTime createdAt;

        private ZonedDateTime updatedAt;
    }
}
