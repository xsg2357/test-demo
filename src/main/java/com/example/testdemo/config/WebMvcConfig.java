package com.example.testdemo.config;

import com.example.testdemo.config.Interceptors.TokenInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptors tokenInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/user/**","/logout","address/*","/file/*")
                .excludePathPatterns("/register","/login");
        super.addInterceptors(registry);
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 当访问/file下的资源时，会到/root/myFile/下去找
        // 例如访问：http://localhost:8080/file/1.png时会去找/root/myFile/1.png
//        registry.addResourceHandler("/file/**").addResourceLocations("file:/root/myFile/");
//        super.addResourceHandlers(registry);
    }
}
