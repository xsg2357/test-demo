package com.example.testdemo;

import com.example.testdemo.config.filter.RequestBodyNullFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<RequestBodyNullFilter> Filters() {
        FilterRegistrationBean<RequestBodyNullFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestBodyNullFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("RequestBodyNullFilter");
        return registrationBean;
    }



}

