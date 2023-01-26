package com.project.staragile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicureService {
	
	@Autowired
	MedicureRepository doctorRepository;
	
	public Doctor registerDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
		}

	public Doctor createDoctor() {
		Doctor doctor = new Doctor("MP1110","Sanket","Neurologist","15 Years");
		return doctorRepository.save(doctor);
	}

	public Doctor getDoctorDetails(String doctorRegistrationId) {
		// TODO Auto-generated method stub
		return doctorRepository.findById(doctorRegistrationId).get();
	}
	
	public Doctor registerDummyDoctor() {
		Doctor doctor = new Doctor("MP1110","Sanket","Neurologist","15 Years");
		return doctor;
	}
	
	public String sayHello() {
		return "Hello from Doctor Shubham";
	}

	public Doctor updateDoctor(String doctorRegistrationId, Doctor docDetails) {
		// TODO Auto-generated method stub
		Doctor doctor = doctorRepository.findById(doctorRegistrationId).get();
		doctor.setDoctorName(docDetails.getDoctorName());
		doctor.setDoctorSpeciality(docDetails.getDoctorSpeciality());
		doctor.setDoctorExperience(docDetails.getDoctorExperience()); 
				
		return doctorRepository.save(doctor);
	}

	public List<Doctor> searchDoctor(String doctorRegistrationId) {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	public void deleteDoctor(String doctorRegistrationId) {
		// TODO Auto-generated method stub
		doctorRepository.deleteById(doctorRegistrationId);
	}

}
