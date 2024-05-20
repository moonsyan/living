package com.hspedu.hspliving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-02 18:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HsplivingGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsplivingGatewayApplication.class, args);
    }
}
