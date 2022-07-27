package com.example.resource.config;

import com.example.resource.interceptor.AuthorizationInterceptor;
import com.example.resource.interceptor.LogInterceptor;
import com.example.resource.resolver.RemoteResourceResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.MalformedURLException;

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

        try {
            registry
                    .addResourceHandler("/remote/**")
                    .addResourceLocations(new UrlResource("https://api.npoint.io/"))
                    .resourceChain(false)
                    .addResolver(new RemoteResourceResolver());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
