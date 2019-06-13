package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Comment;

@Component
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByUserUsername(String user);

}
