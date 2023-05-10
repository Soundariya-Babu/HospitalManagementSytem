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
import com.example.springapp.model.MedicalRecord;
import com.example.springapp.service.MedicalRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

	    @Mock
	    private MedicalRecordService service;

	    @Autowired
	    private MockMvc mockMvc;

	    
	    private static final LocalDate date=LocalDate.of(2023, 05, 05);

	    MedicalRecord mockValue=new MedicalRecord(1L,2L,3L,date,"Fever","Paracetomol","next week visit again");
	    MedicalRecord mockValue1=new MedicalRecord(1L,2L,3L,date,"Fever","Paracetomol","next week visit again");

	    
        List<MedicalRecord> list=Arrays.asList(mockValue,mockValue1);
	    
	     String st = "{\"patientId\":2,\"doctorId\":1,\"diagnosis\":\"fever\",\"prescription\":\"Paracetoml\",\"notes\":\"hi\"}";
	     String st1 = "{\"id\":1,\"patientId\":1,\"doctorId\":2,\"diagnosis\":\"fever\",\"prescription\":\"Paracetoml\",\"notes\":\"hi\"}";

	     
	    Optional<MedicalRecord> optinalMock= Optional.ofNullable(mockValue);

	    @Test
	    public void testGetById() throws Exception {
	    	
	
	        long Id = 1L;
	        String id="1";

	     given(service.getMedicalRecordById(Id)).willReturn(optinalMock);
	     mockMvc.perform(get("/medical-records")
	    		 .param("id", id))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
	       			.andExpect(jsonPath("$").isArray())
	       			.andReturn();
	    }
	    
	    
	    @Test
	    public void testGetAll() throws Exception {
	    	

	        Mockito.when(service.getAllMedicalRecord()).thenReturn(list);
	        mockMvc.perform(get("/medical-records"))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
            .andExpect(content().contentType("application/json"))
   			.andExpect(jsonPath("$").isArray())
   			.andReturn();
	        
	    }

	    @Test
	    public void testCreate() throws Exception {
	    

	     //  Mockito.when(InventoryService.saveInventory(inventory)).thenReturn(inventory);
	        mockMvc.perform(MockMvcRequestBuilders.post("/medical-records")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(st))
	                .andExpect(MockMvcResultMatchers.status().isCreated());

	    }
	   

	    @Test
	    public void testUpdate() throws Exception {
	        
	       // Mockito.when(appointmentService.updateAppointment(mockAppointment1)).thenReturn("Appointment booked");

	        mockMvc.perform(MockMvcRequestBuilders.put("/medical-records")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(st1))
	                .andExpect(MockMvcResultMatchers.status().isOk());

	    }

	    @Test
	    public void testDelete() throws Exception {
	        
	       
	    	 String id="1";
	    		mockMvc.perform(MockMvcRequestBuilders.delete("/medical-records")
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
