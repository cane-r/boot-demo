package com.n11.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.n11.demo.model.Talk;
import com.n11.demo.model.Track;
import com.n11.demo.service.TalkService;
import com.n11.demo.service.TrackService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class MockServiceTest {
	
	@Autowired
	TrackService trackservice;
	@Autowired
	TalkService talkservice;
	
	
	@Test
	public void talkServiceLoads() {
		 assertThat(talkservice).isNotNull();
		
	}
	
	@Test
	public void trackServiceLoads() {
		 assertThat(trackservice).isNotNull();
		
	}
	
	@Test
	public void addTalkTest() {
		
		 Talk talk=new Talk("Test",60);
		 int size=talkservice.getAllTalks().size();
		 talkservice.add(talk);
		 int newsize=talkservice.getAllTalks().size();
		 assertThat(size+1, is(newsize));
	}
	
	@Test
	public void addTrackTest() {
		
		 Track track=new Track();
		 int size=trackservice.getAllTracks().size();
		 trackservice.add(track);
		 int newsize=trackservice.getAllTracks().size();
		 assertThat(size+1, is(newsize));
	}
	
	
	

}
