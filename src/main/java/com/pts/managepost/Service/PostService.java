package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import com.pts.managepost.Entity.Post;

public interface PostService {

	void deleteAll();

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	Optional<Post> findById(Integer id);

	List<Post> findAll(Sort sort);

	Page<Post> findAll(Pageable pageable);

	List<Post> findAll();

	<S extends Post> S save(S entity,Authentication authentication);

	Post update(int id, Post post);

}
