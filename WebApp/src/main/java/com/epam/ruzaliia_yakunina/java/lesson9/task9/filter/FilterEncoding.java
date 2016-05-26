package com.epam.ruzaliia_yakunina.java.lesson9.task9.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FilterEncoding implements Filter {
    private final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        httpresponse.addHeader("Cache-Control", "no-store, no-cache");
        httpresponse.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        chain.doFilter(request, response);

    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
