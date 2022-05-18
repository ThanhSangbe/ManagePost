package com.pts.managepost.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pts.managepost.Entity.Category;
import com.pts.managepost.Repository.CategoryRepository;
import com.pts.managepost.Service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public <S extends Category> S save(S entity) {
		if(!this.categoryRepository.existsByName(entity.getName()))
		return this.categoryRepository.save(entity);
		return null;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
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
		categoryRepository.deleteById(id);
	}

	@Override
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	@Override
	public Category update(Category ca, int id)
	{
		Optional<Category> cate = this.categoryRepository.findById(id);
		if(cate != null)
		{
			ca.setId(cate.get().getId());
			return this.save(ca);
		}
		return null;
		
		
	}
	
}
