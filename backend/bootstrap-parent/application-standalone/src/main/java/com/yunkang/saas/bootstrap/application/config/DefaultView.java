package com.yunkang.saas.bootstrap.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//
//        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
//
//
//            registry.addResourceHandler(staticPathPattern)
//                    .addResourceLocations(
//                            this.resourceProperties.getStaticLocations())
//                    .setCachePeriod(0);
//
//    }
}