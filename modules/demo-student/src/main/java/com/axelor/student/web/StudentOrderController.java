package com.axelor.student.web;



import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import com.axelor.db.JpaSupport;
import com.axelor.inject.Beans;
import com.axelor.meta.CallMethod;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.axelor.rpc.Response;
import com.axelor.student.db.Book;
import com.axelor.student.db.Candidate;
import com.axelor.student.db.Store;
import com.axelor.student.db.Student;
import com.axelor.student.db.repo.StudentRepository;
import com.axelor.student.service.StudentService;

public class StudentOrderController extends JpaSupport {

	@Inject
	private StudentService ss;

	public void calculate(ActionRequest request, ActionResponse response) {
		Store store = request.getContext().asType(Store.class);
		store = ss.calculate(store);

		response.setValue("amount", store.getAmount());
		response.setValue("taxAmount", store.getTaxAmount());
		response.setValue("totalAmount", store.getTotalAmount());

		if (store.getTotalAmount() != null) {
			response.setNotify("Your total amount  : " + store.getTotalAmount());

		}
	}

	public void studentName(ActionRequest request, ActionResponse response) {
		Context context = request.getContext();
		// Context parentContext = context.getParent();

		Student student = context.asType(Student.class);

		if (student.getName() != null) {
			student = Beans.get(StudentRepository.class).findByName(student.getName());

		} else {
			System.err.println("No method");
		}
		System.err.println(student);

		// Beans.get(StudentService.class).sayName(student);
		// response.setAlert("hello");

	}

	public void sayName(ActionRequest request, ActionResponse response) {
		System.err.println("inside method");
		// Student student = new Student();
		// response.setAlert("Comfirm your Name : " + student.getName());

		Student student = request.getContext().asType(Student.class);

		if (student.getName() != null) {
			// response.setAlert("Comfirm Your Name : " + student.getName());
			response.setNotify("Comfirm your name : " + student.getName());
			// response.setFlash("Comfirm your name : " + student.getName());
			// response.setError("Comfirm your name : " + student.getName());
		}
		System.err.println("STudent COntext value as : "+student);

		String name = student.getName(); // set email via name
		name = name.replaceAll("\\s", "");
		response.setAttr("email", "value", name + "@gmail.com");
		response.setAttr("phone", "value", "+91 ");

		// ActionViewBuilder avb =
		// ActionView.define("Books").model(Book.class.getName()).add("grid","book-grid");
		// response.setView(avb.map());

	}

	public void removeDuplicate(ActionRequest request, ActionResponse response) {
		Context context = request.getContext();
		Book book = context.asType(Book.class);

	response.setNotify("Repeat name not allowed : " + ss.book(book));
		
	}
	
	@CallMethod
	public Response validate(String location) {
		Response res = new ActionResponse();
		
		if(location == null) {
			res.addError("location", "location Required");
		}
		System.err.println("Location is : " + location);
		
		return res;
		
	}
	
	
}











