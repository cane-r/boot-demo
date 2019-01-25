package com.n11.demo.configuration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.n11.demo.model.Talk;
import com.n11.demo.model.Track;

@Configuration
public class BeanConfiguration {
	
	

    @Bean(name="talkList")
    public ArrayList<Talk> getTalkList() {
    	return new ArrayList<Talk>();
    }
    
    
    @Bean(name="trackList")
    public ArrayList<Track> getTrackList() {
    	return new ArrayList<Track>();
    }
    
    //can be used for id purposes
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    public AtomicInteger counter() {
    	return new AtomicInteger();
    }
   
    
}
