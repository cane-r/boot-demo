package com.n11.demo.controllers;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.n11.demo.model.Talk;
import com.n11.demo.model.Track;
import com.n11.demo.scheduler.TalkScheduler;
import com.n11.demo.service.TalkService;

@Controller
public class BaseController {
	
	private static final Logger logger=LoggerFactory.getLogger(BaseController.class);
	
	
	private TalkService service;
	private TalkScheduler scheduler;
	
	@Autowired
	public BaseController(TalkService service,TalkScheduler scheduler ) {
		this.service = service;
		this.scheduler=scheduler;
	}

	@RequestMapping("/login")
	public String login() {
		boolean isLoggedIn=false;
		
		
	if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) { 
	    		String user = SecurityContextHolder.getContext().getAuthentication().getName();
	    		isLoggedIn = user.equalsIgnoreCase("admin");
	    		
	    	}	
	if(isLoggedIn) {
		return "redirect:/";
	}
	else
		return "login";
		
	    	
	    }
		
	
	
	@RequestMapping("/logout")
	public void logout() {
		logger.info("logout");
	}
	
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	
	
	@GetMapping("/add")
	public String add(Model model) {
		
		if (!model.containsAttribute("talk")) {
			model.addAttribute("talk", new Talk());
	    }
		
		return "add";
	}
	
	@PostMapping("/add")
	public String add( @Valid @ModelAttribute("talk") Talk talk,Errors errors, 
			RedirectAttributes ra) {
		
		if (errors.hasErrors()) {
		
			ra.addFlashAttribute("talk", talk);
			return "add";
        }
		else {
			
			service.add(talk);
			
			return "redirect:add?success=true";
		}
		
	}

	@GetMapping(value = "/talk-list")
	public String list(Model model) {
		List<Talk> talks=service.getAllTalks();
		model.addAttribute("talks",talks);
		return "talk-list";
	}
	
   @GetMapping(value = "/talk-list/{id}")
	public String specificTalk(@PathVariable(value="id") final int id,Model model) {
	  Talk talk=service.getById(id);
	   model.addAttribute(talk);
	   return "talk";
	  
	}
   
   @GetMapping(value = "/schedule")
	public ModelAndView schedule(Model model) {
		
		
		ModelAndView mav = new ModelAndView();
		List<Track> tracks= scheduler.getScheduledTracks();
	    mav.addObject("schedule",tracks);
	    mav.setViewName("schedule");
	    return mav;
	}
	
  @PostMapping(value = "/schedule")
	public void schedule() {
	   logger.info("not supported");
		throw new RuntimeException("Not supported");
	}
  
  
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException{
        setValue(LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME));
      }

      @Override
      public String getAsText() throws IllegalArgumentException {
        return ((LocalTime) getValue()).toString();
      }
    });
  }


}
