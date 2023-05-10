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
import com.example.springapp.model.Patient;
import com.example.springapp.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class PatientControllerTest {

	    @Mock
	    private PatientService ps;

	    @Autowired
	    private MockMvc mockMvc;
	    
	    Patient patient=new Patient(1L,"abc",30L,"female","Chennai","98989889","abc@gmail.com","nil","no");
	    Patient patient1=new Patient(2L,"qee",30L,"female","Chennai","98989889","qwe@gmail.com","nil","no");

	    
        List<Patient> list=Arrays.asList(patient,patient1);
	    
	    //String st = "{\"id\":1,\"name\":\"Medicine\",\"quantity\":20L,\"category\":\"fever\",\"price\":20L,\"supplier\":\"Parace\"}";

	    Optional<Patient> optinalMock= Optional.ofNullable(patient);

	    @Test
	    public void testGetPatientById() throws Exception {
	    	
	
	        long Id = 1L;

	     given(ps.getPatientById(Id)).willReturn(optinalMock);
	     mockMvc.perform(get("/patient")
	    		 .param("id", "1"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
         .andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$").isArray())
			.andReturn();
	    }
	   
	    @Test
	    public void testGetPatientAll() throws Exception {
	    	
	        Mockito.when(ps.getAllPatient()).thenReturn(list);

	        mockMvc.perform(get("/patient"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
            .andExpect(content().contentType("application/json"))
   			.andExpect(jsonPath("$").isArray())
   			.andReturn();
	    }

	    @Test
	    public void testCreatePatient() throws Exception {
	    

	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);
	        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(patient)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }

	    @Test
	    public void testUpdatePatient() throws Exception {
	        
	       // Mockito.when(appointmentService.updateAppointment(mockAppointment1)).thenReturn("Appointment booked");
	        mockMvc.perform(MockMvcRequestBuilders.put("/patient")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(patient)))
	                .andExpect(MockMvcResultMatchers.status().isOk());

	    }

	    @Test
	    public void testDeletePatient() throws Exception {
	        
	       
	    	String id="1";
    		mockMvc.perform(MockMvcRequestBuilders.delete("/patient")
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
