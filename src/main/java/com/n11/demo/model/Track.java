/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.n11.demo.model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author HOME
 */

public class Track {
    
    
    private String name;
    private int id;
    private final Map<LocalTime,Talk> morningSlot;
    private final Map<LocalTime,Talk> lunchSlot;
    private final Map<LocalTime,Talk> afternoonSlot;
    private Map<LocalTime,Talk> combinedMap;
    
    private LocalTime timestampForMorning;
    private LocalTime timestampForAfternoon; 
    
    private final int morningMinutes=180;
    private final int afternoonMinutes=240;
    
    private int totalMorningSlotAllocatedSoFar=0;
    private int totalAfternoonSlotAllocatedSoFar=0;
   
    public Track(){
        
        
        
       
        
        timestampForMorning=LocalTime.of(9, 0);
        timestampForAfternoon=LocalTime.of(13, 0);
        
        morningSlot=new HashMap<LocalTime, Talk>();
        lunchSlot=new HashMap<LocalTime, Talk>();
        afternoonSlot=new HashMap<LocalTime, Talk>();
        
        lunchSlot.put(LocalTime.of(12, 0),new Talk("Lunch",60));
    }
    
    private boolean addTalkInternal(Talk talk){
        LocalTime talkTime;
        
        if(talk.getDuration()+totalMorningSlotAllocatedSoFar<=morningMinutes){
            talkTime=timestampForMorning.plusMinutes(totalMorningSlotAllocatedSoFar);
            totalMorningSlotAllocatedSoFar+=talk.getDuration();
           // morningSlot.put(talkTime.format(DateTimeFormatter.ISO_LOCAL_TIME), talk);
           morningSlot.put(talkTime, talk);
            //System.out.println(talk + " added to " + this.name + " morning slot");
            return true;
        }
        else if(talk.getDuration()+totalAfternoonSlotAllocatedSoFar<=afternoonMinutes){
            talkTime=timestampForAfternoon.plusMinutes(totalAfternoonSlotAllocatedSoFar);
            totalAfternoonSlotAllocatedSoFar+=talk.getDuration();
            afternoonSlot.put(talkTime, talk);
            //System.out.println(talk + " added to " + this.name + " afternoot slot");
            return true;
        }
        else if (!hasNetworkingSlot()) {
            //can be added to neither morning nor afternoon so suitable for networking.
            
            talkTime=timestampForAfternoon.plusMinutes(totalAfternoonSlotAllocatedSoFar);
            int interval=getRemainingMinute();
            Talk t=new Talk("Networking",interval);
            afternoonSlot.put(talkTime,t);
            totalAfternoonSlotAllocatedSoFar+=interval;
            //System.out.println(t + " added to " + this.name + " afternoon networking slot");
        return false;
        }
        else
        	return false;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(name + System.lineSeparator());
        
        Map<LocalTime, Talk> map=new TreeMap<LocalTime, Talk>();
        map.putAll(morningSlot);
        map.putAll(lunchSlot);
        map.putAll(afternoonSlot);
        map.forEach((LocalTime k,Talk v)->builder.append("(").append(k).append(" - ").append(v.getTitle()).append(")")
        		.append(System.lineSeparator()));
        return builder.toString();
    }
    
    
    
    public Map<LocalTime, Talk> getCombinedMap() {
        
        
        combinedMap=new TreeMap<LocalTime, Talk>();
        combinedMap.putAll(morningSlot);
        combinedMap.putAll(lunchSlot);
        combinedMap.putAll(afternoonSlot);
       
        return combinedMap;
    }
    
    
    public void setCombinedMap(Map<LocalTime, Talk> combinedMap) {
		this.combinedMap = combinedMap;
	}

	public boolean isFull(){
    return (totalAfternoonSlotAllocatedSoFar==afternoonMinutes)&&(totalMorningSlotAllocatedSoFar==morningMinutes);
}
	
	public boolean isEligibleForNetworking() {
//		System.out.println(afternoonSlot.entrySet()
//				.stream()
//				.filter(talk->talk.getValue().getTitle().equalsIgnoreCase("networking)"))
//				.findFirst()
//				.get());
		//afternoonSlot.entrySet().stream().forEach(talk->talk.getValue().getTitle());
		
		//after 16:00
		return totalAfternoonSlotAllocatedSoFar>=180 && !hasNetworkingSlot();
	}
 public boolean addTalk(Talk talk){
     
     if(isFull()){
         return false;
     }
     else{
         return addTalkInternal(talk);
     }
     
 }
 public int getRemainingMinute(){
     return afternoonMinutes-totalAfternoonSlotAllocatedSoFar;
 }
 
 public String getName(){
     return this.name;
 }
 
 public int getId() {
	 return this.id;
 }
 
public void setId(int id) {
	
	this.name="Track-"+id;
	this.id=id;
	
}

private boolean hasNetworkingSlot() {
	boolean condition=afternoonSlot.values().stream().anyMatch(talk->talk.getTitle().equalsIgnoreCase("networking"));
System.out.println(name + " has networking ? " + condition);


return condition;
}
    
    
    
}
