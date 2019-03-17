package com.yourtion.dubbo.two.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yourtion
 */
@SpringBootApplication
@ImportResource(value = {"classpath:spring/spring-jdbc.xml", "classpath:spring/spring-dubbo.xml"})
@MapperScan(basePackages = "com.yourtion.dubbo.two.model.mapper")
public class BootMoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BootMoreApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootMoreApplication.class);
    }

}