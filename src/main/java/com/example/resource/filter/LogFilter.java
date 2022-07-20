package com.example.resource.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("request from {}:{}", servletRequest.getLocalAddr(), servletRequest.getLocalPort());

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("response to {}:{}", servletRequest.getLocalAddr(), servletRequest.getLocalPort());
    }

}