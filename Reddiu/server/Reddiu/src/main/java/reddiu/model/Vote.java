package reddiu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import reddiu.model.user.SecurityUser;

@Entity
@Table(catalog = "reddiu", name = "vote")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private SecurityUser user;
	
	private boolean upvote = false;
	
	private boolean downvote = false;
	
	@ManyToOne
	private Message message;
	
	@ManyToOne
	private Comment comment;

	public Vote() {
		super();
	}

	public Vote(long id, SecurityUser user, boolean upvote, boolean downvote, Comment comment) {
		super();
		this.id = id;
		this.user = user;
		this.upvote = upvote;
		this.downvote = downvote;
		this.comment = comment;
	}

	public Vote(long id, SecurityUser user, boolean upvote, boolean downvote, Message message) {
		super();
		this.id = id;
		this.user = user;
		this.upvote = upvote;
		this.downvote = downvote;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	
	
}
