package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;

import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.model.user.SecurityUserAuthority;



public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	private List<String> roles = new ArrayList<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String username, String password, String firstName,
			String lastName, List<String> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}
	
	public UserDTO(SecurityUser user) {
		this.id = user.getId();
		this.username = user.getUsername(); // password se ne ubacuje u DTO
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		for (SecurityUserAuthority ua: user.getUserAuthorities()) {
			this.roles.add(ua.getAuthority().getName());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
