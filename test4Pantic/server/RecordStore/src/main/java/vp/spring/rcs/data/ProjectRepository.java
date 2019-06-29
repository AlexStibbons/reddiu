package vp.spring.rcs.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Project;

@Component
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public Page<Project> findByNameContainsIgnoreCase(String name, Pageable page);

}
