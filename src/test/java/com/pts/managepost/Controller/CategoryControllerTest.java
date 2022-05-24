package com.pts.managepost.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pts.managepost.Controller.*;
import com.pts.managepost.Service.CategoryService;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean 
	private CategoryService categoryService;
	
	
	
}
