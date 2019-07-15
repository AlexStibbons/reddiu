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
	
	private boolean parentComment;
	
	private long parentCommentId;

	private List<CommentDto> childComments = new ArrayList<>();

	private long score;
	
	public CommentDto() {
		super();
	}
	
	public CommentDto(Comment comment) { 
		this.id = comment.getId();
		this.text = comment.getText();
		this.user = comment.getUser().getUsername();
		if (comment.getParentComment() == null) {
			this.parentComment = true;
		} else {
			this.parentComment = false;
			this.parentCommentId = comment.getParentComment().getId();
		}
		if (!comment.getChildComments().isEmpty()) {
			this.childComments = comment.getChildComments().stream()
								.map(CommentDto::new)
								.collect(Collectors.toList()); 
			}
		this.score = comment.getScore();
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

	public boolean isParentComment() {
		return parentComment;
	}

	public void setParentComment(boolean parentComment) {
		this.parentComment = parentComment;
	}

	public long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	
	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}	
	

}
