package com.example.resource.config;

import com.example.resource.interceptor.AuthorizationInterceptor;
import com.example.resource.interceptor.LogInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/json/**").addResourceLocations("classpath:/json/");
        registry.addResourceHandler("/txt/**").addResourceLocations("classpath:/txt/");
        registry.addResourceHandler("/local/**").addResourceLocations("file:/home/antonsashkou/Desktop/");

        /*registry
                .addResourceHandler("/json/**")
                .addResourceLocations("classpath:/json/")
                .resourceChain(false)
                .addResolver(new EncodedResourceResolver());*/
    }
}
