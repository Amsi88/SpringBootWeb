package com.springBoot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("login");
        registry.addViewController("/formUser").setViewName("formUser");
        //Principal
        registry.addViewController("/indexMenu").setViewName("indexMenu");

        //Form Users Principal
        registry.addViewController("/formAllServices").setViewName("formAllServices");
        registry.addViewController("/formServiceAddress").setViewName("formServiceAddress");

        //Web Principal
        registry.addViewController("/services").setViewName("services");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/about").setViewName("about");
        //web
        registry.addViewController("/portfolio-1-col").setViewName("portfolio-1-col");
        registry.addViewController("/portfolio-2-col").setViewName("portfolio-2-col");
        registry.addViewController("/portfolio-3-col").setViewName("portfolio-3-col");
        registry.addViewController("/portfolio-4-col").setViewName("portfolio-4-col");
        registry.addViewController("/portfolio-item").setViewName("portfolio-item");

        registry.addViewController("/blog-home-1").setViewName("blog-home-1");
        registry.addViewController("/blog-home-2").setViewName("blog-home-2");
        registry.addViewController("/blog-post").setViewName("blog-post");

        registry.addViewController("/full-width").setViewName("full-width");
        registry.addViewController("/sidebar").setViewName("sidebar");
        registry.addViewController("/faq").setViewName("faq");
        registry.addViewController("/error").setViewName("404");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/pricing").setViewName("pricing");

        registry.addViewController("/usersList").setViewName("usersList");
    }

}
