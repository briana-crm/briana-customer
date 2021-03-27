package by.ttre16.briana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BrianaCustomer {
    public static void main(String[] args) {
        SpringApplication.run(BrianaCustomer.class, args);
    }
}
