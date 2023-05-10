package com.example.springapp.controller;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springapp.HospitalManagementSystemApplication;
import com.example.springapp.model.Billing;
import com.example.springapp.service.BillingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class BillingControllerTest {

	    @Mock
	    private BillingService service;

	    @Autowired
	    private MockMvc mockMvc;

	    
	    private static final LocalDate date=LocalDate.of(2023, 05, 05);
	   // private static final LocalTime time=LocalTime.of(9,30);

	    Billing mockValue=new Billing(1L,3L,date,"Description",10L);
	    Billing mockValue1=new Billing(1L,3L,date,"Description",10L);

	    
        List<Billing> list=Arrays.asList(mockValue,mockValue1);
	    
	   // String st = "{\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Para\"}";
	    String st = "{\"patientId\":1,\"treatment_description\":\"fever\",\"amount\":20L}";

	    Optional<Billing> optinalMock= Optional.ofNullable(mockValue);

	    @Test
	    public void testGetById() throws Exception {
	    	
	
	        long Id = 1L;
	        String id="1";

	     given(service.getBillingById(Id)).willReturn(optinalMock);
	     mockMvc.perform(MockMvcRequestBuilders.get("/billing")
	    		 .param("id", id))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(print())
         .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	    }
	    
	    
	    @Test
	    public void testGetAll() throws Exception {
	    	
	        

	        Mockito.when(service.getAllBilling()).thenReturn(list);

	        mockMvc.perform(MockMvcRequestBuilders.get("/billing"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print())
            .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	        
	    }

	    @Test
	    public void testCreate() throws Exception {
	    

	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);

	        mockMvc.perform(MockMvcRequestBuilders.post("/billing")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(st))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	   
	    // Helper method to convert object to JSON string
	    private String asJsonString(Object object) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    }
	    
}
