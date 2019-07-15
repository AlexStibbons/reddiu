package vp.spring.rcs.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.Message;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.service.CommentService;
import vp.spring.rcs.service.MessageService;
import vp.spring.rcs.service.UserDetailsServiceImpl;
import vp.spring.rcs.web.dto.CommentDto;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@GetMapping("api/comments") // useless, but okay
	public ResponseEntity<List<CommentDto>> findAll(){
		List<CommentDto> dtos = commentService.findAll().stream()
								.map(CommentDto::new)
								.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("api/comment/{id}") // useless again, but okay
	public ResponseEntity<CommentDto> findById(@PathVariable long id) {
		Comment found = commentService.findById(id);
		
		if (found!=null) {
			return new ResponseEntity<>(new CommentDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/comment/{id}")
	public ResponseEntity<CommentDto> editComment(@AuthenticationPrincipal User user,
													@PathVariable long id,
													@RequestBody CommentDto edited){
		Comment found = commentService.findById(id);
		
		if (found!=null && found.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
			found.setText(edited.getText());
			found.setScore(edited.getScore());
			found = commentService.save(found);
			return new ResponseEntity<>(new CommentDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("api/comment/{id}") // necessary to leave all child comments present
	public ResponseEntity<CommentDto> deleteComment(@AuthenticationPrincipal User user,
													@PathVariable long id){
		Comment found = commentService.findById(id);
		
		if (found!=null && found.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
			found.setText("[deleted]");
			found = commentService.save(found);
			return new ResponseEntity<>(new CommentDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("api/comment/message/{id}")
	public ResponseEntity<CommentDto> addComment(@AuthenticationPrincipal User user,
												@RequestBody CommentDto comment,
												@PathVariable long id) {
		Message msg = messageService.findById(id);
		SecurityUser commenter = userService.findByUserName(user.getUsername());
		
		Comment newComm = new Comment();
		newComm.setMessage(msg);
		newComm.setUser(commenter);
		newComm.setText(comment.getText());
		newComm.setScore(1);
		newComm = commentService.save(newComm);
		
		// frontend-side, it has to pass comment id if this is a child comment
		if (comment.getParentCommentId() != 0) {
			newComm.setParentComment(commentService.findById(comment.getParentCommentId()));
			newComm = commentService.save(newComm);
		}

		
		return new ResponseEntity<>(new CommentDto(newComm), HttpStatus.OK);
	}
	
}
