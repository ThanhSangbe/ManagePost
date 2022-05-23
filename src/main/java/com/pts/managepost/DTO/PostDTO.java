package com.pts.managepost.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
	private int id;
	private String title;
	private String body;
	private String slug;

	private UserDTO user;

	private CategoryDTO category;
}
