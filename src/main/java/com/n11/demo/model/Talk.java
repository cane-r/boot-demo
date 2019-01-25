package com.n11.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.n11.demo.annotations.DivisibleByFive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Component
//@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)

public class Talk {
	
	
	 private static int i;
	 private int id;
	
	@NotNull
    @Size(min=5, max=50,message="Title length must be at least 5 characters long,up to 50 characters")
	private String title;
	
	@NotNull
    @Min(value=5,message="Duration must be greater than 5 minutes")
	@Max(value=60,message="Duration must be smaller than 60 minutes")
	@DivisibleByFive
	private int duration;
	

	public Talk(String title, int duration) {
		//this.id=Talk.i;
		//Talk.i++;
        this.title = title;
        this.duration = duration;
        //System.out.println("Created Talk " + this);
    }
	
	
	public Talk(Talk talk) {
        this.id=talk.id;
        this.title = talk.getTitle();
        this.duration = talk.getDuration();
        
    }
	
	public int getCount() {
		return Talk.i++;
	}
	

	@Override
    public String toString() {
        
         return "Id=" +id+ ",Title=" + title + ",duration=" + duration + '}';
         
         
    }
	
	public void setId(int id) {
		this.id=id;
	}
	

}
