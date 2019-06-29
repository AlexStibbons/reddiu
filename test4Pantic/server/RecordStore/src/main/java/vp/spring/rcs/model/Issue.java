package vp.spring.rcs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "test4", name = "issue")
public class Issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	private String text;
	
	private long votes;
	
	@ManyToOne
	private Project project;

	public Issue() {
		super();
	}

	public Issue(long id, String username, String text, long votes, Project project) {
		super();
		this.id = id;
		this.username = username;
		this.text = text;
		this.votes = votes;
		this.project = project;
	}

	public Issue(long id, String username, String text, Project project) {
		super();
		this.id = id;
		this.username = username;
		this.text = text;
		this.project = project;
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

	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	


}
