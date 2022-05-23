package com.pts.managepost.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pts.managepost.DTO.CategoryDTO;
import com.pts.managepost.Entity.Category;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Repository.CategoryRepository;
import com.pts.managepost.Service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO save(CategoryDTO entity) {
		Category cate = this.modelMapper.map(entity, Category.class);
		if(!this.categoryRepository.existsByName(cate.getName()))
		{
			cate = this.categoryRepository.save(cate);
			return modelMapper.map(cate, CategoryDTO.class);
		}
		return null;
	}

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(
		category -> modelMapper.map(category, CategoryDTO.class)	
				).collect(Collectors.toList());
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public CategoryDTO findById(Integer id) {
		Category cate = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",String.valueOf(id)));
		return this.modelMapper.map(cate, CategoryDTO.class);
	}
	@Override
	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		CategoryDTO cate = this.findById(id);
		Category c = this.modelMapper.map(cate, Category.class);
		this.categoryRepository.delete(c);
	}

	@Override
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	@Override
	public CategoryDTO update(CategoryDTO ca, int id)
	{
		CategoryDTO cate = this.findById(id);
		ca.setId(cate.getId());
		return this.save(this.modelMapper.map(ca, CategoryDTO.class));

	}

	@Override
	public void deleteCategory(CategoryDTO dto) {
		Category c = this.modelMapper.map(dto, Category.class);
		this.categoryRepository.delete(c);
		
	}
	
}
