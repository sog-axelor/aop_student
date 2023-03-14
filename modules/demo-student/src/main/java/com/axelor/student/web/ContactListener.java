package com.axelor.student.web;

import javax.persistence.EntityListeners;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import com.axelor.student.db.Candidate;

/*@EntityListeners(value = { null })*/
public class ContactListener {
	
	@PostPersist
	@PostUpdate
	private void onpostPersist(Candidate candidate) {
		System.err.println("Candidate saved");
	}
}
