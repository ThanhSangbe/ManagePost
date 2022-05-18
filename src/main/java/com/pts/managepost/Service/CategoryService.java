package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.pts.managepost.Entity.Category;

public interface CategoryService {

	Category getById(Integer id);

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Optional<Category> findById(Integer id);

	Category update(Category ca, int id);

}
