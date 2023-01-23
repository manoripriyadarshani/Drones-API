package com.example.api.drones.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author manorip
 * @create 1/23/23 8:46 PM
 */
@Component
public class MDCFilter implements Filter {

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_LOG_VARIABLE_NAME = "correlationId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String corrId = httpRequest.getHeader(CORRELATION_ID_HEADER_NAME);

        if (corrId == null) {
            corrId = UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_ID_LOG_VARIABLE_NAME, corrId);
        httpResponse.setHeader(CORRELATION_ID_HEADER_NAME, corrId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID_LOG_VARIABLE_NAME);
        }
    }

    @Override
    public void destroy() {
    }
}

