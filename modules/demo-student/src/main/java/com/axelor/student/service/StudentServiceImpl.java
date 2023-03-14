package com.axelor.student.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import com.axelor.common.ObjectUtils;
import com.axelor.meta.CallMethod;
import com.axelor.student.db.Book;
import com.axelor.student.db.BookLine;
import com.axelor.student.db.Store;
import com.axelor.student.db.Student;
import com.axelor.student.db.Tax;

public class StudentServiceImpl implements StudentService {

	@Override
	public String sayName(Student student) {
		return String.format("Welcome '%s!'", student.getName());

	}

	@Override
	public String hello() {
		return "Hello World";
	}

	@Override
	public Store calculate(Store store) {

		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;

		if (ObjectUtils.isEmpty(store.getItems())) {
			return store;
		}

		if (!ObjectUtils.isEmpty(store.getItems())) {

			for (BookLine item : store.getItems()) {
				BigDecimal value = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
				BigDecimal taxValue = BigDecimal.ZERO;

				if (!ObjectUtils.isEmpty(item.getTaxes())) {
					for (Tax tax : item.getTaxes()) {
						taxValue = taxValue.add(tax.getRate().multiply(value));
					}
				}

				amount = amount.add(value);
				taxAmount = taxAmount.add(taxValue);
			}
		}

		store.setAmount(amount.setScale(4, RoundingMode.HALF_UP));
		store.setTaxAmount(taxAmount.setScale(4, RoundingMode.HALF_UP));
		store.setTotalAmount(amount.add(taxAmount).setScale(2, RoundingMode.HALF_UP));

		return store;
	}

	@Override
	public Book book(Book book) {
			String[] bName = {book.getBookName()};
			bName  = Arrays.stream(bName).distinct().toArray(String[]::new);
			 System.out.println("Remove duplicate value " + Arrays.toString(bName));
		return book;
	}



}