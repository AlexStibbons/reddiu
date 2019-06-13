package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vp.spring.rcs.model.Comment;

public class CommentDto {
	
	private long id;
	
	private String text;
	
	private MessageDto message;
	
	private String user;
	
	private List<CommentDto> childComments = new ArrayList<>();

	public CommentDto() {
		super();
	}
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		this.user = comment.getUser().getUsername();
		if (!comment.getChildComments().isEmpty()) {
			this.childComments = comment.getChildComments().stream()
								.map(CommentDto::new)
								.collect(Collectors.toList()); 
			}
		}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageDto getMessage() {
		return message;
	}

	public void setMessage(MessageDto message) {
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<CommentDto> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<CommentDto> childComments) {
		this.childComments = childComments;
	}
	
	

}
