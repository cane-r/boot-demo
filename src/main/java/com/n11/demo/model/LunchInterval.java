package com.n11.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 *
 * @author HOME
 */
@Component("lunch")
public class LunchInterval extends Interval {

   
    List<Talk> talks;
    
    public LunchInterval() {
        super(60);
        talks=new ArrayList<Talk>();
    }

    @Override
    //always full
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean addTalk(Talk talk)  {
        return false;
    }

    @Override
    public boolean canAdd(Talk talk) {
        return false;
    }
    //no talk..empty list.
    @Override
    public List<Talk> getAllTalks() {
         
         talks.add(new Talk("Lunch",this.totalTimeInMinutes));
         return talks;
    }

    //one hour lunch
    @Override
    public int getTotalMinutes() {
        return 60;
    }

    @Override
    public void print() {
     //nothing to print    
    }

    @Override
    public boolean removeTalk(Talk talk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
