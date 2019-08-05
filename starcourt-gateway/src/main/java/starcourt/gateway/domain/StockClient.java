package starcourt.gateway.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient("starcourt-stock")
public interface StockClient {

    @GetMapping("{id}")
    Map<String, Object> getStock(@PathVariable Long id);
}
