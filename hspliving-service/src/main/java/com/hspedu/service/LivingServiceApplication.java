package com.hspedu.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-01 18:01
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LivingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivingServiceApplication.class,args);

    }
}
