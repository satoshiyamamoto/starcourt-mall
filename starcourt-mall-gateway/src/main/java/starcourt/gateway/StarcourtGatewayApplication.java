package starcourt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class StarcourtGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarcourtGatewayApplication.class, args);
    }

}
