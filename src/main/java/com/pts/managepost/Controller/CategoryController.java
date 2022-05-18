package com.pts.managepost.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.Entity.Category;
import com.pts.managepost.Service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping("")
	public List<Category> getAll()
	{
		return this.categoryService.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable int id)
	{
		Optional<Category> cate = this.categoryService.findById(id);
		if(cate != null) return ResponseEntity.ok(cate.get());
		else return ResponseEntity.ok(new String("Id in my system don't exists in my system"));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add( @RequestBody Category ca)
	{
		Category cate =  this.categoryService.save(ca);
		if(cate != null) return ResponseEntity.ok(cate);
		else return ResponseEntity.ok(new String("Error: Category name is exists in my system"));
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<?> update(@Validated @RequestBody Category ca, @PathVariable int id)
	{
		Category cate =  this.categoryService.update(ca,id);
		if(cate != null) return ResponseEntity.ok(cate);
		else return ResponseEntity.ok(new String("Error: Update category fail"));
		
	}
	@GetMapping("/delete/{id}")
	public ResponseEntity<Category> delete(@PathVariable int id)
	{
		Optional<Category> category = this.categoryService.findById(id);
		 return category.map(
				 cate ->{
					 categoryService.deleteById(id); return new ResponseEntity<>(cate,HttpStatus.OK);
				 }).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
