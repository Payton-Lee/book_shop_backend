package com.shop.bookshop.config;

import com.shop.bookshop.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WebMvcConfig implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;
    @Autowired
    public void setTokenInterceptor(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/image/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path path = Paths.get("image");
        String absolutePath = path.toFile().getAbsolutePath();
        registry.addResourceHandler("/image/**").addResourceLocations("file:/" + absolutePath + "/");
    }
}
