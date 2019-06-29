package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.IssueRepository;
import vp.spring.rcs.model.Issue;

@Component
public class IssueService {
	
	@Autowired
	IssueRepository issueRepository;
	
	public List<Issue> findAll(){
		return issueRepository.findAll();
	}
	
	public Issue findById(long id) {
		return issueRepository.findById(id).get();
	}
	
	public Issue save(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public void delete(Issue issue) {
		issueRepository.delete(issue);
	}

}
