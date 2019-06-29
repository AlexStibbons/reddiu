package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Issue;

@Component
public interface IssueRepository extends JpaRepository<Issue, Long>{

}
