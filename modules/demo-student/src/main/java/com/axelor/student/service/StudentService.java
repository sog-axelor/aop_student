package com.axelor.student.service;

import com.axelor.meta.CallMethod;
import com.axelor.student.db.Book;
import com.axelor.student.db.Store;
import com.axelor.student.db.Student;

public interface StudentService {
	
	@CallMethod
	 String sayName(Student student);

	  String hello();
	  
	  Store calculate(Store store);
	  
	  Book book(Book Book);
	
}
