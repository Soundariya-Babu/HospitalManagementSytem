package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Appointment;
import com.example.springapp.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepo;

	@Override
	public String saveAppointment(Appointment appointment) {
		List<Appointment> ap=appointmentRepo.findByDoctorId(appointment.getDoctorId());
        //ap.forEach(n->System.out.println(n));
		if(ap.isEmpty())
		{
			appointmentRepo.save(appointment);
			return "Appointment booked";
		}else {
		for(Appointment app:ap)
		{
			System.out.println(appointment.getDoctorId());
			if(appointment.getDoctorId()==app.getDoctorId())
			{
				if((appointment.getDate()==app.getDate())&&(appointment.getTime()==app.getTime()))
				{
					return "Timeslot not available";
				}
				else {
					appointmentRepo.save(appointment);
					return "Appointment booked";
				}
			}else
			{
				appointmentRepo.save(appointment);
				return "Appointment booked";
			}
		}
		}
		return null;
		
	}

	@Override
	public String updateAppointment(Appointment appointment) {

		if(appointmentRepo.existsById(appointment.getId()))
		{
			List<Appointment> ap=appointmentRepo.findByDoctorId(appointment.getDoctorId());

			for(Appointment app:ap)
			{
				if(appointment.getDoctorId()==app.getDoctorId())
				{
					if(appointment.getDate()==app.getDate()&&appointment.getTime()==app.getTime())
					{
						return "Timeslot not available";
					}
					else {
						appointmentRepo.save(appointment);
						return "Appointment booked";
					}
				}else
				{
					appointmentRepo.save(appointment);
					return "Appointment booked";
				}
			}
			
	    }
		return "Appointment Id not available";
	}

	@Override
	public String deleteAppointment(Long id) {
		if(appointmentRepo.existsById(id))
         {
	       appointmentRepo.deleteById(id);
	       return "Deleted";
          }
		return "Id not exists";
	}

	@Override
	public List<Appointment> getAllAppointment() {
		List<Appointment> appointments = appointmentRepo.findAll();		
		return appointments;
	}

	@Override
	public Optional<Appointment> getAppointmentById(Long id) {
		Optional<Appointment> appointment = appointmentRepo.findById(id);
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentByPatientId(Long patientId) {
		List<Appointment> appointments=appointmentRepo.findByPatientId(patientId);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentByDoctorId(Long doctorId) {
		List<Appointment> appointments= appointmentRepo.findByDoctorId(doctorId);
		return appointments;
	}

}
