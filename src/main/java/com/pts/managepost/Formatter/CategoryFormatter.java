package com.pts.managepost.Formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.pts.managepost.Entity.Category;

public class CategoryFormatter implements Formatter<Category> {

	@Override
	public String print(Category object, Locale locale) {
		return String.valueOf(object.getId());
	}

	@Override
	public Category parse(String text, Locale locale) throws ParseException {
		Category s = new Category();
		s.setId(Integer.parseInt(text));
	return s;
	}
	
}
