package com.devara.springboot.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomHeaderFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    if (httpRequest.getRequestURI().startsWith("/api/")) {
      chain.doFilter(httpRequest, httpResponse);
      return;
    }

    String customHeader = httpRequest.getHeader("X-Custom-Header");
    if (customHeader == null || customHeader.isBlank()) {
      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
