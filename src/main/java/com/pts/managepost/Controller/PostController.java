package com.pts.managepost.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.DTO.PostDTO;
import com.pts.managepost.Entity.Post;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("")
	private List<PostDTO> getAll() {
		return this.postService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable int id) {
			return ResponseEntity.ok(this.postService.findById(id));
	}

	@PostMapping(value = "/add", consumes = { "application/json" })
	public ResponseEntity<PostDTO> add(@RequestBody PostDTO post, Authentication authentication) {
		return ResponseEntity.ok(this.postService.save(post, authentication));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PostDTO> update(@PathVariable int id, @RequestBody PostDTO post) {
		return new ResponseEntity<PostDTO>(this.postService.update(id, post), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		this.postService.deleteById(id);
		return new ResponseEntity<String>("Post deleted successfully!", HttpStatus.OK);
	}
}
