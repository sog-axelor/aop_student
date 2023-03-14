package com.axelor.student;

import com.axelor.app.AxelorModule;
import com.axelor.student.service.StudentService;
import com.axelor.student.service.StudentServiceImpl;

public class StudentModule extends AxelorModule {
		
	@Override
	protected void configure() {
		bind(StudentService.class).to(StudentServiceImpl.class);
	}
	
}
