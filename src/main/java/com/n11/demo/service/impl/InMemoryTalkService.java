package com.n11.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n11.demo.model.Talk;
import com.n11.demo.repository.TalkRepository;
import com.n11.demo.service.TalkService;

@Service
public class InMemoryTalkService<X> implements TalkService {

	
    private TalkRepository talkRepo;
	private int counter=0;
	
    @Autowired
	public InMemoryTalkService(TalkRepository talkRepo) {
		
		this.talkRepo = talkRepo;
	}

	@Override
    public Talk add(Talk talk) {
	talk.setId(counter++);
    return talkRepo.add(talk).get();
    }

    @Override
    public Talk remove(Talk talk) {
    return talkRepo.remove(talk).get();
    }

    @Override
    public List<Talk> getAllTalks() {
         return talkRepo.getAllTalks().get();
    }

    @Override
    public Talk update(Talk talk) {
    
        return talkRepo.update(talk).get();
        
    }

    @Override
    public Talk getById(int id) {
    	//or custom exception here..
    return talkRepo.getById(id).orElseThrow(() -> new IllegalArgumentException("No record found with id " + id));
    }
    
   
}
