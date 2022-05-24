package com.pts.managepost.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pts.managepost.Service.CategoryService;

@SpringBootTest
public class CategoryServiceTest {
	@Autowired
	private CategoryService categoryService;
	@Test
	public void getCategoryCount()
	{
		long ca = this.categoryService.count();
		assertThat(ca).isEqualTo(4);	
	}
}
