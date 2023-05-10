package com.example.springapp.controller;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.example.springapp.model.Pharmacy;
import com.example.springapp.service.PharmacyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class PharmacyControllerTest {

	    @Mock
	    private PharmacyService ps;

	    @Autowired
	    private MockMvc mockMvc;

	    
	    private static final LocalDate date=LocalDate.of(2023, 05, 05);
	   // private static final LocalTime time=LocalTime.of(9,30);

	    Pharmacy pharmacy=new Pharmacy(1L,1L,"paracetomal","200mg",date,123L);
	    Pharmacy pharmacy2=new Pharmacy(2L,2L,"paracomal","20mg",date,123L);

        List<Pharmacy> pharmacyList=Arrays.asList(pharmacy,pharmacy2);
	    
	    String st = "{\"id\":1,\"patientId\":1,\"medication-name\":\"paracetomol\",\"dosage\":\"200mg\",\"prescription-number\":12345}";

	    String st1= "{\"patientId\":1,\"medication-name\":\"paracetomol\",\"dosage\":\"200mg\",\"prescription-number\":12345}";

	    
	    Optional<Pharmacy> optinalMock= Optional.ofNullable(pharmacy);

	    @Test
	    public void testGetInventoryById() throws Exception {
	    	
	
	        long Id = 1L;

	     given(ps.getPharmacyById(Id)).willReturn(optinalMock);
	     mockMvc.perform(get("/pharmacy")
	    		 .param("id", "1"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
         .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	    }
	   
	    @Test
	    public void testGetPharmacyAll() throws Exception {
	    	
	        Mockito.when(ps.getAllPharmacy()).thenReturn(pharmacyList);

	        // Act
	        mockMvc.perform(get("/pharmacy"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
            .andExpect(content().contentType("application/json"))
   			.andExpect(jsonPath("$").isArray())
   			.andReturn();
	        
	    }

	    @Test
	    public void testCreatePharmacy() throws Exception {
	    

	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);
	        mockMvc.perform(MockMvcRequestBuilders.post("/pharmacy")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(st1))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    @Test
	    public void testUpdatePharmacy() throws Exception {
	        
	       // Mockito.when(appointmentService.updateAppointment(mockAppointment1)).thenReturn("Appointment booked");

	        mockMvc.perform(MockMvcRequestBuilders.put("/pharmacy")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(st))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    @Test
	    public void testDeletePharmacy() throws Exception {
	        
	    	String id="1";
    		mockMvc.perform(MockMvcRequestBuilders.delete("/pharmacy")
    				.param("id", id))
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
