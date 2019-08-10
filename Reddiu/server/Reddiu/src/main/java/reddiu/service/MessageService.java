package reddiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import reddiu.data.MessageRepository;
import reddiu.model.Message;

@Component
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	public List<Message> findAllSimple(){
		return messageRepository.findAll();
	}
	
	public Page<Message> findPage(Pageable page) {
		return messageRepository.findAll(page);
	}
	
	public Message findById(long id) {
		return messageRepository.findById(id).get();
	}
	
	public void deleteById(long id) {
		messageRepository.deleteById(id);
	}
	
	public Message save(Message message) {
		return messageRepository.save(message);
	}
	
	public List<Message> findByUser(String user) {
		return messageRepository.findByUserUsername(user);
	}
	
	public Page<Message> findByTitle(String title, Pageable page) {
		return messageRepository.findByTitleContainsIgnoreCase(title, page);
	}
}
