package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vp.spring.rcs.model.Message;

public class MessageDto {
	
	private long id;
	
	private String title;
	
	private String text;
	
	private CategoryDto category;
	
	private String user;
	
	private List<CommentDto> comments = new ArrayList<>();
	
	private long score;

	public MessageDto() {
		super();
	}
	
	public MessageDto(Message message) {
		this.id = message.getId();
		this.title = message.getTitle();
		this.text = message.getText();
		this.category = new CategoryDto(message.getCategory());
		this.user = message.getUser().getUsername();
		this.comments = message.getComments().stream()
						.filter(c -> c.getParentComment() == null)
						.map(CommentDto::new)
						.collect(Collectors.toList());
		this.score = message.getScore();

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
	

}
