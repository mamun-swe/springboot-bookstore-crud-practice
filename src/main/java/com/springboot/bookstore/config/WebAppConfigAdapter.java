package com.springboot.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.bookstore.interceptors.DemoInterceptor;

@Component
public class WebAppConfigAdapter implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(interceptor);
    }
}
