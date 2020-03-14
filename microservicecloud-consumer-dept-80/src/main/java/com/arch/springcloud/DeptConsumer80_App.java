package com.arch.springcloud;

import com.arch.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
/**
 * @RibbonClient：自定义Ribbon轮询规则
 * name：微服务名称
 * configuration：指定自定义配置类
 * 注：类MySelfRule不能在@ComponentScan的扫描路径下，即不能与启动类在同意包或者子包下
 */
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class,args);
    }
}
