package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vp.spring.rcs.model.Project;

public class ProjectDto {
	
	private long id;
	
	private String name;
	
	private String description;
	
	private String readme;
	
	private List<IssueDto> issues = new ArrayList<>();

	public ProjectDto() {
		super();
	}
	
	public ProjectDto(Project project) {
		this.id = project.getId();
		this.name = project.getName();
		this.description = project.getDescription();
		this.readme = project.getReadme();
		
		if (!project.getIssues().isEmpty()) {
			this.issues = project.getIssues().stream()
							.map(IssueDto::new)
							.collect(Collectors.toList());
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

	public List<IssueDto> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueDto> issues) {
		this.issues = issues;
	}
	
	

}
