package starcourt.serviceregistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class StarcourtServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarcourtServiceRegistryApplication.class, args);
    }

}
