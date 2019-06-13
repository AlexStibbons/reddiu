package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Message;

@Component
public interface MessageRepository extends JpaRepository<Message, Long>{
	
	public List<Message> findByUserUsername(String user);

}
