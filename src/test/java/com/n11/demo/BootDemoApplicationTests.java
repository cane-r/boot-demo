package com.n11.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.n11.demo.controllers.BaseController;
import com.n11.demo.repository.TalkRepository;
import com.n11.demo.repository.TrackRepository;
import com.n11.demo.service.TalkService;
import com.n11.demo.service.TrackService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc() 

//spring boot integration test with @springboottest
public class BootDemoApplicationTests {
	
	
	@Autowired
    private WebApplicationContext webAppContext;

	
	@Autowired
    private BaseController controller;
	
	@Autowired
    private TrackRepository trackRepository;
	
	
	@Autowired
    private TalkRepository talkRepository;
	
	
	@Autowired
    private TrackService trackService;
	
	@Autowired
    private TalkService talkService;
	
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void contextLoads() {
		 assertThat(controller).isNotNull();
		 assertThat(trackRepository).isNotNull();
		 assertThat(talkRepository).isNotNull();
		 assertThat(trackService).isNotNull();
		 assertThat(talkService).isNotNull();
		 assertThat(mockMvc).isNotNull();
		
	}

	
	
	@Test
	@WithMockUser
	public void submitSuccess() throws Exception {
		
		
		
		
		
		
		
		MvcResult res=mockMvc.perform(formLogin("/login").user("admin").password("admin")).andReturn();
		 System.out.println(res.getResponse().getCookies().length);
		
		RequestBuilder postrequest = post("/add").cookie(res.getResponse().getCookies()) 
	    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .param("title", "Test")
				.param("duration", "55");
		
		
		
		this.mockMvc.perform(postrequest)
				    .andExpect(redirectedUrl("/add"))
				    .andExpect(view().name("add"));
				    
				
				
				
	}
	
	
	@Test
	public void testLogin() throws Exception {
//	    RequestBuilder requestBuilder = post("/login")
//	    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//	            .param("username", "admin")
//	            .param("password", "admin");
	    
//	    mockMvc.perform(post("/login").with((user("admin").password("pass").roles("USER","ADMIN"))))
//	            .andDo(print())
//	            .andExpect(status().is3xxRedirection())
//	            .andExpect(view().name("/index")).andExpect(authenticated());
	    
	    mockMvc.perform(formLogin("/login").user("admin").password("admin")).andDo(print())
	    .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/index")).andExpect(authenticated());
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webAppContext)
				.apply(springSecurity())
				.build();
	}

	
	

	

}

