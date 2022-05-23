package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.pts.managepost.DTO.CategoryDTO;
import com.pts.managepost.Entity.Category;

public interface CategoryService {

	Category getById(Integer id);

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	List<Category> findAll(Sort sort);

	List<CategoryDTO> findAll();

	CategoryDTO  save(CategoryDTO entity);

	CategoryDTO findById(Integer id);

	CategoryDTO update(CategoryDTO ca, int id);
	
	void deleteCategory(CategoryDTO dto);


}
