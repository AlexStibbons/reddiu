package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vp.spring.rcs.model.Comment;

public class CommentDto {
	
	private long id;
	
	private String username;
	
	private String text;
	
	private long newsId;
	
	private long parentCommentId;
	
	private List<CommentDto> childComments = new ArrayList<>();
	
	private boolean isParentComment;

	public CommentDto() {
		super();
	}
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.username = comment.getUsername();
		this.text = comment.getText();
		this.newsId = comment.getNews().getId();
		if (comment.getParentComment() == null) {
			this.isParentComment = true;
		} else {
			this.isParentComment = false;
			this.parentCommentId = comment.getParentComment().getId();
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public List<CommentDto> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<CommentDto> childComments) {
		this.childComments = childComments;
	}

	public boolean isParentComment() {
		return isParentComment;
	}

	public void setParentComment(boolean isParentComment) {
		this.isParentComment = isParentComment;
	}
	
	

}
