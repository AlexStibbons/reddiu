package reddiu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reddiu.data.VoteRepository;
import reddiu.model.Vote;

@Component
public class VoteService {

	@Autowired
	VoteRepository voteRepository;
	
	public Vote findById(long id) {
		return voteRepository.findById(id).get();
	}
	
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}
	
	public Vote findByMsgUser(long id, String username) {
		return voteRepository.findByMessageIdAndUserUsername(id, username);
	}
	
	public Vote findByCommUser(long id, String username) {
		return voteRepository.findByCommentIdAndUserUsername(id, username);
	}
	
}
