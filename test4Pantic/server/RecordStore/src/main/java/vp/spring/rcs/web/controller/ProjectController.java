package vp.spring.rcs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Issue;
import vp.spring.rcs.model.Project;
import vp.spring.rcs.service.IssueService;
import vp.spring.rcs.service.ProjectService;
import vp.spring.rcs.web.dto.IssueDto;
import vp.spring.rcs.web.dto.PageDto;
import vp.spring.rcs.web.dto.ProjectDto;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	IssueService issueService;
	
	// findall paginated [though unnecessary, bc there is a title search already] -- ok
	
	@GetMapping("api/projects")
	public ResponseEntity<PageDto> findAll(Pageable page){
		
		PageDto dto = new PageDto(projectService.findAll(page));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	 
	// find by title paginated -- ok	
	@GetMapping("api/projects/search")
	public ResponseEntity<PageDto> findByName(@RequestParam(required = false, defaultValue = "") String name, 
											Pageable page){
		
		PageDto dto = new PageDto(projectService.findByName(name, page));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// find by id -- ok
	
	@GetMapping("api/projects/{id}") 
	public ResponseEntity<ProjectDto> findById(@PathVariable long id) {
		Project found = projectService.findById(id);
		
		if (found != null) {
			ProjectDto dto = new ProjectDto(found);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} 
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// add issue -- ok
	@PostMapping("api/issue/projects/{projectId}")
	public ResponseEntity<IssueDto> addIssue(@PathVariable long projectId,
											@RequestBody IssueDto addIssue) {
		
		Project found = projectService.findById(projectId);
		
		Issue newIssue = new Issue();
		newIssue.setUsername(addIssue.getUsername());
		newIssue.setText(addIssue.getText());
		newIssue.setVotes(0);
		newIssue.setProject(found);
		
		newIssue = issueService.save(newIssue);
		
		return new ResponseEntity<>(new IssueDto(newIssue), HttpStatus.OK);
	}
	
	// save -- logged in only -- ok
	@PreAuthorize("isAuthenticated()")
	@PostMapping("api/projects/new")
	public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto newProject){
		
		Project added = new Project();
		added.setName(newProject.getName());
		added.setDescription(newProject.getDescription());
		added.setReadme(newProject.getReadme());
		added = projectService.save(added);
		
		return new ResponseEntity<>(new ProjectDto(added), HttpStatus.CREATED);
		
	}
	
	// edit -- logged in only -- ok
	@PreAuthorize("isAuthenticated()")
	@PutMapping("api/projects/{id}")
	public ResponseEntity<ProjectDto> editProject(@PathVariable long id, 
													@RequestBody ProjectDto toEdit){
		Project found = projectService.findById(id);
		
		found.setName(toEdit.getName());
		found.setDescription(toEdit.getDescription());
		found.setReadme(toEdit.getReadme());
		found = projectService.save(found);
			
		return new ResponseEntity<>(new ProjectDto(found), HttpStatus.OK);
			
	}
	
	// delete -- logged in only -- ok
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("api/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable long id) {
		
		Project found = projectService.findById(id);
		
		issueService.findAll().stream()
				.filter(issue -> issue.getProject().getId() == id)
				.forEach(issue -> issueService.delete(issue));
		
		projectService.delete(found);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
