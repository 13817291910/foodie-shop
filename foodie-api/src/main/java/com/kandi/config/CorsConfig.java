package com.kandi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//跨域请求配置类 Cross-Origin Resource Sharing
@Configuration
public class CorsConfig {

    public CorsConfig(){
    }


    /**
     * 处是全局跨域设置，如果需要更细粒度的局部跨域，
     * 可以在controller上使用@CrossOrigin注解
     */
    @Bean
    public CorsFilter corsFilter(){
        //添加Cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //设置允许访问的网站域名
        configuration.addAllowedOrigin("http://localhost:8080");
        //设置是否发送cookie信息
        configuration.setAllowCredentials(true);
        //设置http method请求的限制，比如只能get请求，此处不作限制
        configuration.addAllowedMethod("*");
        //放行哪些原始请求头部信息
        configuration.addAllowedHeader("*");
        //为Url添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",configuration);

        return new CorsFilter(corsConfigurationSource);
    }
}
