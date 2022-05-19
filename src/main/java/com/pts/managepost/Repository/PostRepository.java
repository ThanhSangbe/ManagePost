package com.pts.managepost.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pts.managepost.Entity.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
