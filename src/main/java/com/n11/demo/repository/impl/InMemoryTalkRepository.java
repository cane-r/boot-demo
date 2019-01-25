package com.n11.demo.repository.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.n11.demo.model.Talk;
import com.n11.demo.repository.TalkRepository;

@Repository
public class InMemoryTalkRepository implements TalkRepository{
    
	
	@Autowired
	//@Value("getTalkList")
    private List<Talk> talkRepo;

    public InMemoryTalkRepository() {
    }
    
    

    @Override
    public Optional<Talk> add(Talk talk) {
    talkRepo.add(talk);
    return Optional.ofNullable(talk);
    }

    @Override
    public Optional<Talk> remove(Talk talk) {
    talkRepo.remove(talk);
    return Optional.ofNullable(talk);
    }

    @Override
    public Optional<List<Talk>> getAllTalks() {
        return Optional.ofNullable(talkRepo);
    }

    @Override
    public Optional<Talk> update(Talk talk) {
    Talk t=talkRepo.stream().filter(p->p.getId()==talk.getId()).findFirst().get();
    t.setDuration(talk.getDuration());
    t.setTitle(talk.getTitle());
    return Optional.ofNullable(t);
    }

    @Override
    public Optional<Talk> getById(int id) {
       return talkRepo.stream().filter(p->p.getId()==id).findFirst(); 
    }
    
    
}
