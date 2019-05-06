package com.fx.file.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private IpInterceptor interceptor;
    @Value("${ipInterceptor}")
    private boolean ipIsInceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(ipIsInceptor)
            registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
