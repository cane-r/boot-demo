package com.n11.demo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.n11.demo.model.Talk;
import com.n11.demo.service.TalkService;
import com.n11.demo.service.TrackService;


//for now scan every bit of the application context
@SpringBootApplication(scanBasePackages={"com.n11.demo.*"})
public class BootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}
	
     @Component
     class CommandLineDataLoader implements CommandLineRunner{
        

    		TalkService talkService;
    	    TrackService trackService;

    	    @Autowired
    	    public CommandLineDataLoader(TalkService talkService,TrackService trackService) {
    	        this.talkService = talkService;
    	        this.trackService = trackService;
    	         
    	        
    	    }
    	    

    	
    


		//or data can be loaded here sequentially.
		@Override
		public void run(String... args) throws Exception {

			Logger.getLogger(this.getClass().getName()).info("Command line runner");
			//full of meaningless data be tested. 
			talkService.add(new Talk("Talk1",60));
			talkService.add(new Talk("Talk2",60));
			talkService.add(new Talk("Talk3",45));
			talkService.add(new Talk("Talk4",60));
			talkService.add(new Talk("Talk5",45));
	        talkService.add(new Talk("Talk6",45));
	        talkService.add(new Talk("Talk7",50));
	        talkService.add(new Talk("Talk8",60));
	        talkService.add(new Talk("Talk9",15));
	        talkService.add(new Talk("Talk10",45));
	        talkService.add(new Talk("Talk11",55));
	        talkService.add(new Talk("Talk12",15));
	        talkService.add(new Talk("Talk13",35));
	        talkService.add(new Talk("Talk14",45));
	        talkService.add(new Talk("Talk15",55));
	        talkService.add(new Talk("Talk16",5));
	        talkService.add(new Talk("Talk17",35));
	        talkService.add(new Talk("Talk18",60));
	        talkService.add(new Talk("Talk19",40));
	        talkService.add(new Talk("Talk20",60));
	        talkService.add(new Talk("Talk21",60));
	    	
			
		}
    	
    }

}

