package com.pts.managepost.DTO;

import java.util.Collection;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pts.managepost.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	public static String roleUser = "ROLE_USER";
	public static String roleAdmin="ROLE_ADMIN";
	private int id;
	private String username;
	private String role;
	private Collection<Post> posts;
	private boolean active;
}
