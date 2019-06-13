package vp.spring.rcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vp.spring.rcs.model.user.SecurityUser;

@Entity
@Table(catalog = "reddiu", name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String text;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private SecurityUser user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Message message;


	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Comment parentComment;
	
	@OneToMany(mappedBy = "parentComment")
	private Set<Comment> childComments = new HashSet<>();

	public Comment() {
		super();
	}

	public Comment(long id, String text, SecurityUser user, Message message) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.message = message;
	}
	
	/*public Comment(long id, String text, SecurityUser user, Message message, Set<Comment> childComment) {
	super();
	this.id = id;
	this.text = text;
	this.user = user;
	this.message = message;
	this.childComment = childComment;
}*/


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

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Set<Comment> getChildComments() {
		return childComments;
	}

	public void setChildComments(Set<Comment> childComment) {
		this.childComments = childComment;
	}
	
	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	
	

}
