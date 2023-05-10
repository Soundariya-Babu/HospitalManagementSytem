package com.example.springapp.controller;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.example.springapp.model.Appointment;
import com.example.springapp.service.AppointmentService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;





@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HospitalManagementSystemApplication.class)
@AutoConfigureMockMvc
public class AppointmentControllerTest {

	    @Mock
	    private AppointmentService appointmentService;

	    @Autowired
	    private MockMvc mockMvc;

	    @JsonProperty("date")
	    private static final LocalDate date=LocalDate.of(2023, 05, 05);
	    
	    @JsonProperty("time")
	    private static final LocalTime time=LocalTime.of(9,30);

	    
	    Appointment mockAppointment1= new Appointment(1L,2L,3L,date,time,"1 hour","Active");
	    Appointment mockAppointment2= new Appointment(2L,3L,4L,date,time,"1 hour","Active");
	    
	    String st = "{\"patientId\":1,\"doctorId\":2,\"duration\":\"1 hour\",\"status\":\"Active\"}";
	    String st1 = "{\"id\":1,\"patientId\":1,\"doctorId\":2,\"duration\":\"1 hour\",\"status\":\"Active\"}";

	    
	    List<Appointment> mockAppointmentList= Arrays.asList(mockAppointment1,mockAppointment2);
	    Optional<Appointment> optinalMockAppointment= Optional.ofNullable(mockAppointment1);

	    @Test
	    public void testGetAppointmentById() throws Exception {
	    	
	
	        String Id = "1";
	        Long id=1L;

	     given(appointmentService.getAppointmentById(id)).willReturn(optinalMockAppointment);
	     mockMvc.perform(get("/appointment/")
	                .param("id", Id))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
	        		.andExpect(jsonPath("$").isArray())
	        		.andReturn();
	    }
	    @Test
	    public void testGetAppointmentByDoctorId() throws Exception {
	    	
	        // Arrange
	        long Id = 1L;
	        String doctorId="1";

	      Mockito.when(appointmentService.getAppointmentByDoctorId(Id)).thenReturn(mockAppointmentList);

	        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/doctorId", Id)
	        .param("doctorId", doctorId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
        .andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$").isArray())
		.andReturn();

	    }
	    @Test
	    public void testGetAppointmentByPatientId() throws Exception {
	    	
	        // Arrange
	        long Id = 1L;
	        String patientId="1";

	       Mockito.when(appointmentService.getAppointmentByPatientId(Id)).thenReturn(mockAppointmentList);

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/")
	        		.param("patientId", patientId))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$").isArray())
					.andReturn();
	               

	    }
	    @Test
	    public void testGetAppointmentAll() throws Exception {
	    	
	        // Arrange

	        Mockito.when(appointmentService.getAllAppointment()).thenReturn(mockAppointmentList);

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$").isArray())
					.andReturn();


	        
	    }

	    @Test
	    public void testCreateAppointment() throws Exception {
	    

	       Mockito.when(appointmentService.saveAppointment(mockAppointment1)).thenReturn("Appoinment booked");

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.post("/appointment/")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(st))
	                .andExpect(MockMvcResultMatchers.status().isCreated())
	                .andReturn();
	        
	        

	    }

	    @Test
	    public void testUpdateAppointment() throws Exception {
	        
	        Mockito.when(appointmentService.updateAppointment(mockAppointment1)).thenReturn("Appointment booked");

	        // Act
	        mockMvc.perform(MockMvcRequestBuilders.put("/appointment/")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(st1))
	                .andExpect(MockMvcResultMatchers.status().isCreated())
	                .andReturn();

	    }

	    @Test
	    public void testDeleteAppointment() throws Exception {
	        
	       String id="1";
	       mockMvc.perform(delete("/appointment/")
	                .param("id", id))
					.andExpect(status().isOk())
					.andReturn();
	    	
	        
	    }
	    // Helper method to convert object to JSON string
	    private String asJsonString(Object object) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    }
	    
}
