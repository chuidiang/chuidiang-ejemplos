package com.chuidiang.example.bootprimefaces;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;

@EnableAutoConfiguration
@ComponentScan({"com.chuidiang.example.bootprimefaces"})
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.xhtml");
    }
//
//    @Bean
//    public FilterRegistrationBean rewriteFilter() {
//        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
//        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
//                DispatcherType.ASYNC, DispatcherType.ERROR));
//        rwFilter.addUrlPatterns("/*");
//        return rwFilter;
//    }
}
