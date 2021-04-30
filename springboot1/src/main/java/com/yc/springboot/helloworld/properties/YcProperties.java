package com.yc.springboot.helloworld.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-24 10:28
 */
@ConfigurationProperties(prefix = "yc")

public class YcProperties {
    private String name;
    private String age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
