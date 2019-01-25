package com.n11.demo.scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.n11.demo.model.Talk;
import com.n11.demo.model.Track;
import com.n11.demo.service.TalkService;
import com.n11.demo.service.TrackService;

@Component("talkService")
public class TalkScheduler {
	
	
	private TalkService talkService;
	private TrackService trackService;

    @Autowired
    public TalkScheduler(TalkService talkService,TrackService trackService) {
        this.talkService = talkService;
        this.trackService = trackService;
       
    }
    
    
    
    public List<Track> getScheduledTracks(){
        //delete tracks and rearrange them
    	trackService.deleteAll();
        List<Talk> talks=talkService.getAllTalks().stream().collect(Collectors.toList());
        List<Track> tracks=trackService.getAllTracks();
        
       
        //first sort by descending order to place longer talks first.a strategy is a strategy..
        Collections.sort(talks, new Comparator<Talk>() {

			@Override
			public int compare(Talk o1, Talk o2) {
				
				if(o1.getDuration()<o2.getDuration())
					return 1;
				else if(o1.getDuration()==o2.getDuration())
					return 0;
				else
					return -1;
			}
        	
        });
        
        boolean added=false;
        for(Talk talk:talks){
            
            for(int i=0;i<tracks.size();i++){
                
                Track t=tracks.get(i);
            
                if(t.addTalk(talk)){
                    added=true;
                    break;
                }
                added=false;
            }
            if(!added){
                Track tr=trackService.create();
             tr.addTalk(talk);
            }
                 
        }
        
        //normalize..add networking if possible to avoid empty intervals after 16:00
        for(int i=0;i<tracks.size();i++){
                //System.out.println(tracks.size());
                Track t=tracks.get(i);
                if(t.isEligibleForNetworking()){
                    System.out.println(t.getName() + " is not full..Adding networking");
                    t.addTalk(new Talk("Networking",t.getRemainingMinute()));
                }
       }
        return trackService.getAllTracks();
        } 
     
    
 }
    
    

