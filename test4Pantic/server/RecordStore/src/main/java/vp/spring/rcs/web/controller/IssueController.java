package vp.spring.rcs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Issue;
import vp.spring.rcs.service.IssueService;
import vp.spring.rcs.web.dto.IssueDto;

@RestController
public class IssueController {
	
	@Autowired
	IssueService issueService;
	
	// vote up -- ok
	@PreAuthorize("isAuthenticated()")
	@PutMapping("api/issue/up/{issueId}")
	public ResponseEntity<IssueDto> voteUp(@PathVariable long issueId) {
		
		Issue found = issueService.findById(issueId);
		
		found.setVotes(found.getVotes() + 1);
		found = issueService.save(found);
		
		return new ResponseEntity<>(new IssueDto(found), HttpStatus.OK);
	}
	
	
	// vote down -- ok
	@PreAuthorize("isAuthenticated()")
	@PutMapping("api/issue/down/{issueId}")
	public ResponseEntity<IssueDto> voteDown(@PathVariable long issueId) {
		
		Issue found = issueService.findById(issueId);
		
		found.setVotes(found.getVotes() - 1);
		found = issueService.save(found);
		
		return new ResponseEntity<>(new IssueDto(found), HttpStatus.OK);
	}
	

}
