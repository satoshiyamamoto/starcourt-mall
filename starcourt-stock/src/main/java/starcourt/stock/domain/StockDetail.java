package starcourt.stock.domain;

import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Value
public class StockDetail {

    private Long productId;

    private Long quantity;

    private Status status;

    private List<Stock> details;

    private ZonedDateTime updatedAt;

    public static StockDetail of(List<Stock> stocks) {
        Long productId = stocks.stream()
                .map(Stock::getProductId)
                .distinct()
                .findFirst()
                .orElse(null);
        Long receiving = stocks.stream()
                .mapToLong(Stock::getReceiving)
                .filter(Objects::nonNull)
                .sum();
        Long shipping = stocks.stream()
                .mapToLong(Stock::getShipping)
                .filter(Objects::nonNull)
                .sum();
        ZonedDateTime updatedAt = stocks.stream()
                .max(Comparator.comparing(Stock::getUpdatedAt))
                .map(Stock::getUpdatedAt)
                .orElse(ZonedDateTime.now());
        Long quantity = receiving - shipping;
        Status status = quantity > 0 ? Status.AVAILABLE : Status.UNAVAILABLE;

        return new StockDetail(productId, quantity, status, stocks, updatedAt);
    }
}
