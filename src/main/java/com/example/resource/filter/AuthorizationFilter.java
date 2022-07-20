package com.example.resource.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private final ObjectMapper mapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*if (!authorized(servletRequest)) {
            log.error("not authorized");
            prevent(servletResponse);
            return;
        }
*/
        log.info("authorized");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean authorized(ServletRequest servletRequest) {
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var authorization = httpServletRequest.getHeader("Authorization");

        return authorization != null && authorization.equals("123");
    }

    private void prevent(ServletResponse response) throws IOException {
        var httpServletResponse = (HttpServletResponse) response;

        var errorDetails = new HashMap<String, String>();
        errorDetails.put("message", "Invalid token");

        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
    }
}
