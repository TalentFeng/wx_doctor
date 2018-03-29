package com.lqf.wxdoctor.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.lqf.wxdoctor.service", "com.lqf.wxdoctor.web"})
@MapperScan("com.lqf.wxdoctor.dao")
public class WxDoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxDoctorApplication.class, args);
	}

}
