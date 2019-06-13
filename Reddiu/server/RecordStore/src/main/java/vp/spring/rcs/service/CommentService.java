package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.CommentRepository;
import vp.spring.rcs.model.Comment;

@Component
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}
	
	public Comment findById(long id) {
		return commentRepository.findById(id).get();
	}
	
	public void deleteById(long id) {
		commentRepository.deleteById(id);
	}
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public List<Comment> findByUser(String user){
		return commentRepository.findByUserUsername(user);
	}
	
}
