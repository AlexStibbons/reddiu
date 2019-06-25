package vp.spring.rcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "news", name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	private String text;
	
	@ManyToOne
	private News news;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Comment parentComment;
	
	@OneToMany(mappedBy = "parentComment")
	private Set<Comment> childComments = new HashSet<>();

	public Comment() {
		super();
	}

	public Comment(long id, String username, String text, News news) {
		super();
		this.id = id;
		this.username = username;
		this.text = text;
		this.news = news;
	}

	public Comment(long id, String username, String text, Comment parentComment, News news) {
		super();
		this.id = id;
		this.username = username;
		this.text = text;
		this.parentComment = parentComment;
		this.news = news;
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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public Set<Comment> getChildComments() {
		return childComments;
	}

	public void setChildComments(Set<Comment> childComments) {
		this.childComments = childComments;
	}
	
	

}
