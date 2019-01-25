package com.n11.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.n11.demo.configuration.WebMvcConfiguration;
import com.n11.demo.controllers.BaseController;
import com.n11.demo.service.TalkService;


@RunWith(SpringRunner.class)
@WebMvcTest(BaseController.class)
@ContextConfiguration(classes = WebMvcConfiguration.class)

public class BaseControllerTest {

	@Autowired
    private WebApplicationContext webAppContext;
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;


    @Test
    public void testAnonymousPageVisitsShouldReturn401() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/list"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
      mockMvc.perform(MockMvcRequestBuilders.get("/add"))
      .andExpect(MockMvcResultMatchers.status().is4xxClientError());
      
      mockMvc.perform(MockMvcRequestBuilders.get("/talk-list"));
                 
     }
    
    
    @Test
    public void testAnonymousPageVisitsShouldReturn401AndRedirectedToLogin() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/add"));
        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(redirectedUrl("http://localhost:8082/conf-planner/login"));
    }
    
    
    @Before
    public void setUp() {
        this.mockMvc =MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .apply(springSecurity()).addFilters(springSecurityFilterChain)  
                .build();
    }
    @Test
    @WithUserDetails("admin")
    @WithMockUser(value = "admin", password = "admin")
    public void testIfLoggedUserHasAccessToRegularHomePage() throws Exception {
    	
    	 RequestBuilder requestBuilder = formLogin("/login").user("admin").password("admin");
    	 final ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("/"));
    }
    
 
    @Test
    public void loginPageAvailableForAnonymousAccess() throws Exception {
      mockMvc
              .perform(get("/login"))
              .andExpect(status().isOk());
    }
    
    
    @Test
    public void ShouldAddTalkToModelAndReturnView() throws Exception {
        
 
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/add"))
                .andExpect(forwardedUrl("/add"))
                .andExpect(model().attribute("talk", hasSize(1))).
        andExpect(model().attribute("todo", hasProperty("id", isEmptyOrNullString())))
        .andExpect(model().attribute("todo", hasProperty("duration", isEmptyOrNullString())))
        .andExpect(model().attribute("todo", hasProperty("title", isEmptyOrNullString())));
                
    }
    
    
    @Test
    public void loginTestWithFilterchain() throws Exception {
        

        MvcResult result =
                mockMvc.perform(formLogin("/login").user("admin").password("admin"))
                .andExpect(authenticated())
                        .andReturn();
        
        assertEquals(302, result.getResponse().getStatus());
        
        
}
    
	
    
    
    
}
