package com.n11.demo.configuration;





import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.n11.demo.converters.DateTimeToStringConverter;

@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/add.html").setViewName("add");
        registry.addViewController("/list.html").setViewName("list");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateTimeToStringConverter());
       
    }
    
    
  
    
}