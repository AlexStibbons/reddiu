package reddiu.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import reddiu.model.Message;

@Component
public interface MessageRepository extends JpaRepository<Message, Long>{
	
	public List<Message> findByUserUsername(String user); // make it a page
	public Page<Message> findByTitleContainsIgnoreCase(String title, Pageable page);

}
