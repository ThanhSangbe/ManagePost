package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import com.pts.managepost.DTO.PostDTO;
import com.pts.managepost.Entity.Post;

public interface PostService {

	void deleteAll();

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	PostDTO findById(Integer id);

	List<Post> findAll(Sort sort);

	Page<Post> findAll(Pageable pageable);

	List<PostDTO> findAll();

	PostDTO save(PostDTO entity,Authentication authentication);

	PostDTO update(int id, PostDTO post);

	void delete(PostDTO entity);

}
