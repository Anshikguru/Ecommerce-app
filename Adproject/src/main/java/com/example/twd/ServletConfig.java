package com.example.twd;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServlet;
@Configuration
public class ServletConfig {
    @Bean
    ServletRegistrationBean<HttpServlet> helloServlet() {
        return new ServletRegistrationBean<>(new RegisterServlet(), "/register");
    }
}