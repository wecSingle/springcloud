package com.arch.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    /**
     * RestTemplate提供了多种便捷访问远程Http服务的方法
     * 是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集
     *
     * @LoadBalanced：获得Rest时加入Ribbon的配置
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * Robbin核心组件IRule
     * 修改默认的轮询策略
     * @return
     */
    @Bean
    public IRule myRule(){
        //默认的轮询算法
        //return new RoundRobinRule();
        //随机算法
        //return new RandomRule();

        //先按照轮询算法获取服务，如果获取服务失败则在指定的时间内会进行重试，获取可用的服务
        return new RetryRule();
    }
}
