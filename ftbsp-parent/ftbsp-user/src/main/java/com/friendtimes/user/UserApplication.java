package com.friendtimes.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.friendtimes.ucenter.dao")
@EntityScan("com.friendtimes.domain.user.ext") // 扫描实体类
@ComponentScan(basePackages = { "com.friendtimes.api" }) // 扫描接口
@ComponentScan(basePackages = { "com.friendtimes.common" }) // 扫描common下的所有类
@ComponentScan(basePackages = { "net.bojoy" }) // 扫描net.bojoy下的所有类
@ComponentScan(basePackages = { "com.friendtimes.user.common" }) // 扫描common下的所有类
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}
