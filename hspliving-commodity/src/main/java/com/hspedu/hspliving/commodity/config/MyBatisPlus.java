package com.hspedu.hspliving.commodity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-04 15:49
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.hspedu.hspliving.commodity.dao")
public class MyBatisPlus {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        //做基本的设置
        //溢出总页数，设置第一页
        paginationInterceptor.setOverflow(true);

        ////单页限制100条，小于0如-1不受限制
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }


}
