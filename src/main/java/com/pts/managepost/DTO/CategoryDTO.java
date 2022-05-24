package com.pts.managepost.DTO;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.pts.managepost.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
	private int id;
	private String name;
	private String slug;
	private Collection<Post> posts;
}
