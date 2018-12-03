package com;

import com.demo.client.domain.DomainFeignClient;
import com.demo.client.domain.DomainResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ClientApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientApplication.class, args);

        DomainFeignClient client = context.getBean(DomainFeignClient.class);
        DomainResult result = client.get("1A424405387247B79A8823F7B94E6140");
        System.out.println(result);
    }
}
