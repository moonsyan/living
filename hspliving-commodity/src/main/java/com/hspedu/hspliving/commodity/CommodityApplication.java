package com.hspedu.hspliving.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-04-28 12:00
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CommodityApplication {
    public static void main(String[] args) {

        SpringApplication.run(CommodityApplication.class,args);
    }
}
