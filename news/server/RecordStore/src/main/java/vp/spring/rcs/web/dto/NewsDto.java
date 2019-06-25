package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vp.spring.rcs.model.News;

public class NewsDto {
	
	private long id;
	
	private String title;
	
	private String description;
	
	private String text;
	
	private CategoryDto category;
	
	private List<CommentDto> comments = new ArrayList<>();

	public NewsDto() {
		super();
	}
	
	public NewsDto(News news) {
		this.id = news.getId();
		this.title = news.getTitle();
		this.text = news.getText();
		this.description = news.getDescription();
		this.category = new CategoryDto(news.getCategory());
		this.comments = news.getComments().stream()
							.filter(c -> c.getParentComment() == null)
							.map(CommentDto::new)
							.collect(Collectors.toList());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	

}
