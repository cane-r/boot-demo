package com.n11.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 *
 * @author HOME
 */
@Component("networking")
public class NetworkingInterval extends Interval {
         
    //private Talk talk;
    List<Talk> talks;
    
    public NetworkingInterval() {
        super(0);
        talks=new ArrayList<Talk>();
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean addTalk(Talk talk) {
    
        talk.setTitle("Networking");
        talk.setDuration(0);
        this.talks.add(talk);
        
        return true;
        
    }
    @Override
    public boolean canAdd(Talk talk) {
         return false;    
    }

    @Override
    public List<Talk> getAllTalks() {
       
         return this.talks;
    }

    @Override
    public int getTotalMinutes() {
        return 0;
    }

    @Override
    public void print() {
       getAllTalks().stream().forEach(System.out::println);
    }

    @Override
    public boolean removeTalk(Talk talk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

