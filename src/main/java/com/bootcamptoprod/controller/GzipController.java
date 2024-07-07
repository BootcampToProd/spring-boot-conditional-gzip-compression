package com.bootcamptoprod.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GzipController {

    @GetMapping("/large-response")
    public String getLargeResponse() {
        return largeResponse();
    }

    @GetMapping("/exclude-compression")
    public String excludeCompression() {
        return largeResponse();
    }

    private String largeResponse() {
        return "Hello, World! ".repeat(1000000);
    }
}
