package com.pts.managepost.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pts.managepost.DTO.CategoryDTO;
import com.pts.managepost.DTO.PostDTO;
import com.pts.managepost.DTO.UserDTO;
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
	@Autowired 
	private ModelMapper modelMapper;
	@Override
	public PostDTO  save(PostDTO entity, Authentication authentication) {
		String username =  authentication.getName();
		UserDTO user = userService.findByUsername(username);
		entity.setUser(user);
		CategoryDTO c = this.categoryService.findById(entity.getCategory().getId());
		entity.setCategory(c);
		this.postRepository.save(this.modelMapper.map(entity, Post.class));
		return entity;
	}

	@Override
	public List<PostDTO> findAll() {
		return postRepository.findAll().stream().map(
			post -> this.modelMapper.map(post, PostDTO.class)	
				).collect(Collectors.toList());
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
	public PostDTO findById(Integer id) {

		Post p = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(id)));
		return this.modelMapper.map(p, PostDTO.class);
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
		PostDTO p = this.findById(id);
		postRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		postRepository.deleteAll();
	}
	@Override
	public void delete(PostDTO entity) {
		
		postRepository.delete(this.modelMapper.map(entity, Post.class));
	}

	@Override
	public PostDTO update(int id, PostDTO post)
	{
		PostDTO d = this.findById(id);
		Post p = this.modelMapper.map(post, Post.class);
		p.setId(id);
		return modelMapper.map(this.postRepository.save(p),PostDTO.class);
	}
	
	
}
