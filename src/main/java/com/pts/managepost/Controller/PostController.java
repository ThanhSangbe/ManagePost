package com.pts.managepost.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.Entity.Post;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("")
	private List<Post> getAll() {
		return this.postService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable int id) {
		try {
			Post post = this.postService.findById(id).get();
			return new ResponseEntity<>(post, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/add", consumes = { "application/json" })
	public ResponseEntity<Post> add(@RequestBody Post post, Authentication authentication) {

		Post p = this.postService.save(post, authentication);
		return ResponseEntity.ok(p);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Post> update(@PathVariable int id, @RequestBody Post post) {
		return new ResponseEntity<Post>(this.postService.update(id, post), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		try {
			this.postService.deleteById(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("Post deleted successfully!", HttpStatus.OK);
	}
}
