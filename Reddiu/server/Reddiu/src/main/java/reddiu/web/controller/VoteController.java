package reddiu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reddiu.model.Comment;
import reddiu.model.Message;
import reddiu.model.Vote;
import reddiu.service.CommentService;
import reddiu.service.MessageService;
import reddiu.service.UserDetailsServiceImpl;
import reddiu.service.VoteService;
import reddiu.web.dto.VoteDto;

@RestController
public class VoteController {

	@Autowired
	VoteService voteService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@GetMapping("api/vote/msg/{id}")
	public ResponseEntity<VoteDto> getMsgVote(@AuthenticationPrincipal User user,
												@PathVariable long id){
		
		Vote found = voteService.findByMsgUser(id, user.getUsername());
		
		if (found == null) {
			Vote defaultVote = new Vote();
			defaultVote.setMessage(messageService.findById(id));
			defaultVote.setUser(userService.findByUserName(user.getUsername()));
			defaultVote = voteService.save(defaultVote);
			return new ResponseEntity<>(new VoteDto(defaultVote), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}	
	}
	
	@GetMapping("api/vote/comm/{id}")
	public ResponseEntity<VoteDto> getCommVote(@AuthenticationPrincipal User user,
												@PathVariable long id) {
		Vote found = voteService.findByCommUser(id, user.getUsername());
		
		if (found == null) {
			Vote defaultVote = new Vote();
			defaultVote.setComment(commentService.findById(id));
			defaultVote.setUser(userService.findByUserName(user.getUsername()));
			defaultVote = voteService.save(defaultVote);
			return new ResponseEntity<>(new VoteDto(defaultVote), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}
	}
	
	/* A NOTE ABOUT UP/DOWNVOTING
	 * 
	 * - the voting will work only if there is a vote object already created
	 * - the vote object is *only* created when http get request for getMsgVote or getCommVote is called
	 * - therefore, a get request must be placed BEFORE an upvote/downvote request
	 * This means:
	 * - frontend *must* call those get requests once comments and posts(a.k.a. messages) are loaded,
	 *	 BEFORE any kind of voting
	 * - this ensures that once the user wants to up/downvote, there will be a vote object present to
	 * 	 serve its purpose
	 * 
	*/
	
	@PutMapping("api/upvote")
	public ResponseEntity<VoteDto> upvote(@AuthenticationPrincipal User user, @RequestBody VoteDto voted) {
		
		if (voted.getMessageId() != 0) {
			
			// find and upvote message
			Message msg = messageService.findById(voted.getMessageId());
			msg.setScore(msg.getScore() + 1);
			msg = messageService.save(msg);
			
			// change the vote variables according to data passed on from frontend
			
			// why is a null here, if this particular vote object was created already??
			// the 'finding' in get requests above works just fine
			//Vote found = voteService.findByMsgUser(voted.getMessageId(), user.getUsername()); 
			Vote found = voteService.findById(voted.getId()); // works just find if vote object if found via id
			found.setDownvote(voted.isDownvote());
			found.setUpvote(voted.isUpvote());
			found = voteService.save(found);
			
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}

		if (voted.getCommentId() != 0) {
			
			// set score for comment
			Comment comm = commentService.findById(voted.getCommentId()); 
			comm.setScore(comm.getScore() + 1);
			comm = commentService.save(comm);
			
			// update vote according to data passed on from frontend
			Vote found = voteService.findById(voted.getId());
			found.setUpvote(voted.isUpvote());
			found.setDownvote(voted.isDownvote());
			found = voteService.save(found);
			
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
	}
	
	@PutMapping("api/downvote")
	public ResponseEntity<VoteDto> downvote(@AuthenticationPrincipal User user, @RequestBody VoteDto voted) {
		
		if (voted.getMessageId() != 0) {
			
			// find and upvote message
			Message msg = messageService.findById(voted.getMessageId());
			msg.setScore(msg.getScore() - 1);
			msg = messageService.save(msg);
			
			// change the vote variables according to data passed on from frontend
			Vote found = voteService.findById(voted.getId());
			found.setDownvote(voted.isDownvote());
			found.setUpvote(voted.isUpvote());
			found = voteService.save(found);
			
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}

		if (voted.getCommentId() != 0) {
			
			// set score for comment
			Comment comm = commentService.findById(voted.getCommentId());
			comm.setScore(comm.getScore() - 1);
			comm = commentService.save(comm);
			
			// update vote according to data passed on from frontend
			Vote found = voteService.findById(voted.getId());
			found.setUpvote(voted.isUpvote());
			found.setDownvote(voted.isDownvote());
			found = voteService.save(found);
			
			return new ResponseEntity<>(new VoteDto(found), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
	}
}
