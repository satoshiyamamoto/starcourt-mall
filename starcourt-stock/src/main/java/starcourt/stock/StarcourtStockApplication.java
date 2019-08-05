package starcourt.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StarcourtStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarcourtStockApplication.class, args);
	}

}
