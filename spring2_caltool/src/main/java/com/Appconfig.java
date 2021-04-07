package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 09:36
 */
@Configuration
@ComponentScan(basePackages = "com")
public class Appconfig {
    @Bean
    public Random r() {
        return new Random();
    }
}
