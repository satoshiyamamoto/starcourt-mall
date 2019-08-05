package starcourt.gateway.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient("starcourt-catalog")
public interface CatalogClient {

    @GetMapping("{id}")
    Map<String, Object> getCatalog(@PathVariable Long id);
}
