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
import com.n11.demo.repository.TalkRepository;
import com.n11.demo.repository.TrackRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class MockRepositoryTest {
	
	@Autowired
	TalkRepository talkrepository;
	
	@Autowired
	TrackRepository trackrepository;
	
	@Test
	public void talkRepoLoads() {
		 assertThat(talkrepository).isNotNull();
		
	}
	
	@Test
	public void trackRepoLoads() {
		 assertThat(trackrepository).isNotNull();
		
	}
	
	@Test
	public void addTalkTest() {
		
		 Talk talk=new Talk("Test",60);
		 int size=talkrepository.getAllTalks().get().size();
		 talkrepository.add(talk);
		 int newsize=talkrepository.getAllTalks().get().size();
		 assertThat(size+1, is(newsize));
	}
	
	
	@Test
	public void addTalkTestById() {
		
		 Talk talk=new Talk("Test",60);
		 int id=talk.getId();
		 talkrepository.add(talk);
		 Talk t=talkrepository.getById(id).get();
		 assertThat(id, is(t.getId()));
	}
	
	
	
	
	@Test
	public void addTrackTest() {
		
		 Track track=new Track();
		 int size=trackrepository.getAllTracks().get().size();
		 trackrepository.add(track);
		 int newsize=trackrepository.getAllTracks().get().size();
		 assertThat(size+1, is(newsize));
	}
	
	
}
