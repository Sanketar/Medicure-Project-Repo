package com.project.staragile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class TestMedicureService {
	
	@MockBean
	MedicureRepository doctorRepository; 
	
	@Autowired
	MedicureService doctorService;
	
	@Test
	public void testRegisterDoctor() {
		Doctor doctor = new Doctor("MP1110","Sanket","Neurologist","15 Years");
		assertEquals(doctor.getDoctorRegistrationId(), doctorService.registerDummyDoctor().getDoctorRegistrationId());
	}
	
//	@Test
//	void testsearchDoc() {
//		MedicureService pService = new MedicureService();
//		assertEquals(null,pService.searchDoctor(null));
//	}
	
//	@Test
//	 public void testSearchDoctor(){
//		List<Doctor> doctor = doctorService.findAll();
//		assertThat(doctor.size()).isEqualTo(null);
//	 }
	
	@Test
	void testdeletePolicy() {
		Doctor doctor = new Doctor("MP1110","Sanket","Neurologist","15 Years");
		doctorRepository.delete(doctor);
		Doctor doctor1 = null;
        Optional<Doctor> optionalP = doctorRepository.findById("MP1110");
        if(optionalP.isPresent()){
        	doctor1 = optionalP.get();
        }
        assertThat(doctor1).isNull();
	}

	
}
