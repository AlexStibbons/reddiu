package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Comment;

@Component
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
