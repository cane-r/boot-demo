package com.n11.demo.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 *
 * @author HOME
 */
@Component("morning")
public class MorningInterval extends Interval{

    private final List<Talk> talks;
    
    public MorningInterval() {
        super(180);
        this.talks=new ArrayList<Talk>();
    }
    
    
    @Override
    public boolean addTalk(Talk talk) {
        if(isFull( ) || !canAdd(talk)){
            return false;
        }
        else{
            talks.add(talk);
            return true;
        }
    }
      //gets sum of all talk's durations.
    @Override
    public int getTotalMinutes() {
        return getAllTalks().stream().mapToInt(talk->talk.getDuration()).sum();
        
    }

    @Override
    public boolean isFull() {
       return getTotalMinutes() >=this.totalTimeInMinutes;
    }

    @Override
    public List<Talk> getAllTalks() {
        return this.talks;
     
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

