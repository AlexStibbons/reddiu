package reddiu.web.dto;

import reddiu.model.Category;

public class CategoryDto {
	
	private long id;
	
	private String name;

	public CategoryDto() {
		super();
	}
	
	public CategoryDto(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
