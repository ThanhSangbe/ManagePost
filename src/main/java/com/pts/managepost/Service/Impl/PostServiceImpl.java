package com.pts.managepost.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pts.managepost.Entity.Category;
import com.pts.managepost.Entity.Post;
import com.pts.managepost.Entity.User;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Repository.PostRepository;
import com.pts.managepost.Service.CategoryService;
import com.pts.managepost.Service.PostService;
import com.pts.managepost.Service.UserService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	@Override
	public Post  save(Post entity, Authentication authentication) {
		String username =  authentication.getName();
		User user = userService.findByUsername(username);
		entity.setUser(user);
		Category c = this.categoryService.findById(entity.getCategory().getId()).get();
		entity.setCategory(c);
		return postRepository.save(entity);
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public List<Post> findAll(Sort sort) {
		return postRepository.findAll(sort);
	}

	@Override
	public Optional<Post> findById(Integer id) {
		if(!this.postRepository.existsById(id))
			throw new ResourceNotFoundException("Post","id",String.valueOf(id) );
		return postRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return postRepository.existsById(id);
	}

	@Override
	public long count() {
		return postRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		
		if(!this.postRepository.existsById(id))
			throw new ResourceNotFoundException("Post","id",String.valueOf(id) );
		postRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		postRepository.deleteAll();
	}
	@Override
	public Post update(int id, Post post)
	{
		Post p = this.postRepository.findById(id).get();
		if(p != null)
		{
			post.setId(p.getId());
			this.postRepository.save(post);
		}
		throw new ResourceNotFoundException("Post","id",String.valueOf(id) );
	}
	
	
}
