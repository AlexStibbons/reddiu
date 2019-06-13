package vp.spring.rcs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
}
