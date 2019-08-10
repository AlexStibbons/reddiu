package reddiu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import reddiu.model.Vote;

@Component
public interface VoteRepository extends JpaRepository<Vote, Long> {

	public Vote findByMessageIdAndUserUsername(long id, String username);
	public Vote findByCommentIdAndUserUsername(long id, String username);
	

}
