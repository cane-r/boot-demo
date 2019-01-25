package com.n11.demo.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author HOME
 * 4 interval at conference day.
 * Morning(09:00-12:00 makes 180 min),Lunch(12:00-13:00 makes 60 min),
 * Afternoon(13:00-17:00 makes 240 min),Networking(varies according to afternoon)
 */
public abstract class Interval {
    
    
    protected final int totalTimeInMinutes;
    
    
    public Interval(int totalTimeInMinutes) {
        this.totalTimeInMinutes = totalTimeInMinutes;
     }
    
    public abstract boolean isFull();
    
    public boolean canAdd(Talk talk){
         int temp=this.totalTimeInMinutes;
        return talk.getDuration()<=temp-getTotalMinutes();
    }
    
    public int getTotalTimeInMinutes(){
        return this.totalTimeInMinutes;
    }
    
    //can use exceptions here if interval is full,but boolean is simpler.
    public abstract boolean addTalk(Talk talk) ;
    
    public abstract List<Talk> getAllTalks();
    
    public abstract void print();
    
    public abstract int getTotalMinutes();
    
    public abstract boolean removeTalk(Talk talk);
    
}
