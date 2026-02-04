package com.microedge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This maps the URL path "/uploads/**" to the physical folder
        // Use "file:uploads/" for a relative path or "file:/C:/path/to/uploads/" for absolute
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}