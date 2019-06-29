package vp.spring.rcs.web.dto;

import vp.spring.rcs.model.Issue;

public class IssueDto {
	
	private long id;
	
	private String username;
	
	private String text;
	
	private long votes;
	
	private long projectId;
	
	
	public IssueDto() {
		super();
	}
	
	public IssueDto(Issue issue) {
		this.id = issue.getId();
		this.username = issue.getUsername();
		this.text = issue.getText();
		this.votes = issue.getVotes();
		this.projectId = issue.getProject().getId();
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

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	
	
	

}
