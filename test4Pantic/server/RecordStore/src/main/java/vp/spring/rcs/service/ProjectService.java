package vp.spring.rcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.IssueRepository;
import vp.spring.rcs.data.ProjectRepository;
import vp.spring.rcs.model.Project;

@Component
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	
	public Page<Project> findAll(Pageable page){
		return projectRepository.findAll(page);
	}
	
	public Page<Project> findByName(String name, Pageable page) {
		return projectRepository.findByNameContainsIgnoreCase(name, page);
	}
	
	public Project findById(long id) {
		return projectRepository.findById(id).get();
	}
	
	public Project save(Project project) {
		return projectRepository.save(project);
	}
	
	public void delete(Project project) {
		projectRepository.delete(project);
	}

}
