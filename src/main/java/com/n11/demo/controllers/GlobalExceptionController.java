package com.n11.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateProcessingException;

@ControllerAdvice
public class GlobalExceptionController {
	  public static final String ERROR_VIEW = "error";
        
	  
	  
	  

	 
	  @ExceptionHandler(NoHandlerFoundException.class)
	    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
	            ModelAndView mav = new ModelAndView("index");
	            mav.addObject("exception", e);  
	            return mav;
	    }
	  
	  
	  @ExceptionHandler(value = TemplateProcessingException.class)
	  public ModelAndView
	  templateErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	    
	   
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e.getMessage());
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName(ERROR_VIEW);
	    return mav;
	  }
	  
	  
	  
	//handles everything else.
	  @ExceptionHandler(value = Exception.class)
	  public ModelAndView
	  defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	    
	   
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e.getMessage());
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName(ERROR_VIEW);
	    return mav;
	  }
	  
	  
	  
	  
	  
	  
	  
	}