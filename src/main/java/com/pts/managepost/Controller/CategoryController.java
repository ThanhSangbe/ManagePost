package com.pts.managepost.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.DTO.CategoryDTO;
import com.pts.managepost.Entity.Category;
import com.pts.managepost.Entity.Post;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public List<CategoryDTO> getAll() {
		return this.categoryService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable int id) {
		return ResponseEntity.ok(this.categoryService.findById(id));
		
	}

	@PostMapping("/add")
	public ResponseEntity<?> add( @RequestBody CategoryDTO ca) {
		CategoryDTO cate = this.categoryService.save(ca);
		if (cate != null)
			return new ResponseEntity<CategoryDTO>(cate, HttpStatus.CREATED);
		return new ResponseEntity<>(new String("Error: Category name is exists in my system"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Validated @RequestBody CategoryDTO ca, @PathVariable int id) {
			return ResponseEntity.ok(this.categoryService.update(ca, id));	
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		this.categoryService.deleteById(id);
		return new ResponseEntity<String>(new String("Category deleted successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/allPost/{id}")
	public ResponseEntity<?> getAllPostById(@PathVariable int id)
	{
		CategoryDTO category = this.categoryService.findById(id);
		return new ResponseEntity<Collection<Post>>(category.getPosts(),HttpStatus.OK);
	}

}
