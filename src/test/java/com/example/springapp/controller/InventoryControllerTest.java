package com.example.springapp.controller;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.example.springapp.model.Inventory;
import com.example.springapp.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class InventoryControllerTest {

	    @Mock
	    private InventoryService InventoryService;

	    @Autowired
	    private MockMvc mockMvc;

	    

	    Inventory inventory=new Inventory(1L,"name",200L,"category",20L,"supplier");
	    Inventory inventory1=new Inventory(2L,"name1",20L,"category",20L,"supplier");
        List<Inventory> inventoryList=Arrays.asList(inventory1,inventory);
	    
	    String st = "{\"id\":1,\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Para\"}";
	    String st1 = "{\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Para\"}";

	    
	    Optional<Inventory> optinalMockInventory= Optional.ofNullable(inventory);

	    @Test
	    public void testGetInventoryById() throws Exception {
	    	
	
	        long Id = 1L;
            String id="1";
	     given(InventoryService.getInventoryById(Id)).willReturn(optinalMockInventory);
	     mockMvc.perform(get("/inventory")
	    		 .param("id", id))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
	       			.andExpect(jsonPath("$").isArray())
	       			.andReturn();
	    }
	   
	    @Test
	    public void testGetAll() throws Exception {
	    	
	        Mockito.when(InventoryService.getAllInventory()).thenReturn(inventoryList);
	        mockMvc.perform(get("/inventory"))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(print())
		                .andExpect(content().contentType("application/json"))
		       			.andExpect(jsonPath("$").isArray())
		       			.andReturn();
	        
	    }

	    @Test
	    public void testCreateInventory() throws Exception {
	    

	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.post("/inventory")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(inventory)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    @Test
	    public void testUpdate() throws Exception {
	        
	       // Mockito.when(appointmentService.updateAppointment(mockAppointment1)).thenReturn("Appointment booked");

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.put("/inventory")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(inventory)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    @Test
	    public void testDelete() throws Exception {
	        
	       String Id="1";
	    		mockMvc.perform(MockMvcRequestBuilders.delete("/inventory")
	    				.param("id", Id))
	    						.andDo(print())
	    						.andExpect(status().isOk())
	    						.andReturn();
	    	
	        
	    }
	    // Helper method to convert object to JSON string
	    private String asJsonString(Object object) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    }
	    
}
