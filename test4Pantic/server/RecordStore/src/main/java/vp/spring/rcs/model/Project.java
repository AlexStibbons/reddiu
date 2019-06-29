package vp.spring.rcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "test4", name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	private String readme;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	private Set<Issue> issues = new HashSet<>();

	public Project() {
		super();
	}
	
	public Project(long id, String name, String description, String readme) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.readme = readme;
	}


	public Project(long id, String name, String description, String readme, Set<Issue> issues) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.readme = readme;
		this.issues = issues;
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

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}
	
	
	
	

}
