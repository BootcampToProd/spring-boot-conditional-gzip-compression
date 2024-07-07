package com.bootcamptoprod.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class GzipResponseCompressionFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDE_GZIP_URLS = List.of("/exclude-compression");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (acceptsGZipEncoding(request) && !EXCLUDE_GZIP_URLS.contains(request.getRequestURI())) {
            GzipHttpServletResponseWrapper gzipResponse = new GzipHttpServletResponseWrapper(response);
            gzipResponse.setHeader("Content-Encoding", "gzip");
            filterChain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }
}

