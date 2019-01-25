/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.n11.demo.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author HOME
 * Represents a track that holds all intervals
 */
public class Track2 {
    
    private List<Interval> intervals;
    private  String name;
    private static int i=0;
    private  int id;
    private  Interval morning;
    private  Interval afternoon;
    private  Interval lunch;
    //private final Interval networking;

    public Track2() {
        this.name="Track-"+(Track2.i);
        i++;
        this.id=i;
        this.morning = new MorningInterval();
        this.lunch = new LunchInterval();
        this.afternoon = new AfternoonInterval();
        //this.networking = new NetworkingInterval();
        this.intervals=new ArrayList();
    }
    
    public boolean isFull(){
        return morning.isFull() && afternoon.isFull();
    }
    //redundant but whatevs
    public boolean canAdd(Talk talk){
        return morning.canAdd(talk) || afternoon.canAdd(talk);
    }
    

    public boolean addTalk(Talk talk){
       
        final boolean added;
        
        if(morning.canAdd(talk)){
            morning.addTalk(talk);
            added=true;
            System.out.println(talk + " added to morning " + this.name);
        }
        else if(afternoon.canAdd(talk)){
            afternoon.addTalk(talk);
            
            added=true;
            System.out.println(talk + " added to afternoon " + this.name);
        }
        //cant add to afternoon slot but there is still time so networking can be added.
        else if(!afternoon.canAdd(talk) && 
                (afternoon.getTotalTimeInMinutes()-afternoon.getTotalMinutes())>0
                ){
           int duration=afternoon.getTotalTimeInMinutes()-afternoon.getTotalMinutes();
            Talk temp=new Talk("Networking",duration);
            //networking.addTalk(temp);,
            afternoon.addTalk(talk);
            added=false;
        
        }
        //cant add .. full.
        else{
             added=false;  
            }
        return added;
    }
    public void prettyPrint(){
        System.out.println("-- " +this.name +" --");
        getAllTalks().stream().forEach(System.out::println);
        //Stream.of(morning,lunch,afternoon).forEach(interval->interval.print());
    }
    public List<Talk> getAllTalks(){
        
        return getAllIntervals().stream()
                .flatMap(interval->interval.getAllTalks().stream()).collect(Collectors.toList());
    }
    
    public List<Interval> getAllIntervals(){
        intervals.add(morning);
        intervals.add(lunch);
        intervals.add(afternoon);
        //intervals.add(networking);
        return intervals;
    }
    
    //not gonna be used but should be here
    public Talk removeTalk(Talk talk){
        if(morning.getAllTalks().indexOf(talk)>0){
            morning.getAllTalks().remove(talk);
        }
        else if(afternoon.getAllTalks().indexOf(talk)>0) {
            afternoon.getAllTalks().remove(talk);
        }
        
        return talk;
    }
    
    @Override
    public String toString() {
        //return "Track{" + "name=" + name + '}';
        return name;
    }

    public String getName() {
        return name;
    }
    
    
}
