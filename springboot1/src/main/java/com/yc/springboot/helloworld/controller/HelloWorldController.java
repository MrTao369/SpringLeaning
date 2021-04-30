package com.yc.springboot.helloworld.controller;

import com.yc.springboot.helloworld.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-22 18:25
 */
@RestController
public class HelloWorldController {

    //创建日志对象
    private static Log log = LogFactory.getLog(HelloWorldController.class);//必须写当前实现类

    @Autowired
    private Environment env;

    @Value("${yc.name}")
    private String tname;

    @Autowired
    private YcProperties yp;

    @GetMapping("/hello")  //get请求   请求路径为 /hello
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {    //@RequestParam请求参数name
        log.debug("===========debug==========");
        log.info("===========info===========");
        log.error("===========error==========");
        log.fatal("==============fatal===========");

        log.info("系统环境变量信息如下：" + env);
        log.info("用户路径:" + env.getProperty("user.home"));
        log.info("JAVA_HOME:" + env.getProperty("JAVA_HOME"));

        log.info("@Value拿Ycproties的属性:" + tname);
        log.info("@Autowired拿Ycproties的属性:" + yp.getName() + "   " + "年龄:" + yp.getAge());
        log.info("env拿Ycproties的属性:" + env.getProperty("yc.name") + "   " + env.getProperty("yc.age"));


        return String.format("Hello %s!", name);
    }
}
