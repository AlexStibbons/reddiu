package reddiu.web.dto;

import java.util.List;

public class UserProfileDto {
	
	private String username;
	private List<MessageDto> messages;
	private List<CommentDto> comments;
	
	public UserProfileDto() {
		super();
	}

	public UserProfileDto(String username, List<MessageDto> messages, List<CommentDto> comments) {
		super();
		this.username = username;
		this.messages = messages;
		this.comments = comments;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	
}
