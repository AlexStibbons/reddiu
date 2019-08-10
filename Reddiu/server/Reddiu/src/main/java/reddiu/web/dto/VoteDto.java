package reddiu.web.dto;

import reddiu.model.Vote;

public class VoteDto {

	private long id;
	
	private String username;
	
	private long messageId;
	
	private long commentId;
	
	private boolean upvote;
	
	private boolean downvote;

	public VoteDto() {
		super();
	}
	
	public VoteDto(Vote vote) {
		this.id = vote.getId();
		this.username = vote.getUser().getUsername();
		if (vote.getMessage() != null) {
			this.messageId = vote.getMessage().getId();
		}
		if (vote.getComment() != null) {
			this.commentId = vote.getComment().getId();
		}
		this.upvote = vote.isUpvote();
		this.downvote = vote.isDownvote();
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

	public boolean isUpvote() {
		return upvote;
	}

	public void setUpvote(boolean upvote) {
		this.upvote = upvote;
	}

	public boolean isDownvote() {
		return downvote;
	}

	public void setDownvote(boolean downvote) {
		this.downvote = downvote;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	
	
	
	
}
