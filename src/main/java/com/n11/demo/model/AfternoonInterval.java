package com.n11.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


/**
 *
 * @author HOME
 */
@Component("afternoon")
public class AfternoonInterval extends Interval {

    private final List<Talk> talks;
    
    public AfternoonInterval() {
        super(240);
        this.talks=new ArrayList<Talk>();
    }

    @Override
    public boolean isFull() {
        return getTotalMinutes() >=this.totalTimeInMinutes;
    }

    @Override
    public boolean addTalk(Talk talk) {
        
        if(isFull() && !canAdd(talk)){
            return false;
        }
        else{
            talks.add(talk);
            //System.out.println(talk + " added to afternoon");
            return true;
        }    
    
    }

    @Override
    public void print() {
         getAllTalks().stream().forEach(System.out::println);
    }

    @Override
    public int getTotalMinutes() {
         return getAllTalks().stream().mapToInt(talk->talk.getDuration()).sum();
    }

    @Override
    public List<Talk> getAllTalks() {
        return this.talks;    
    }

    @Override
    public boolean removeTalk(Talk talk) {
        return this.getAllTalks().remove(talk);
    }
    
}
