package vp.spring.rcs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Message;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.service.CategoryService;
import vp.spring.rcs.service.MessageService;
import vp.spring.rcs.service.UserDetailsServiceImpl;
import vp.spring.rcs.web.dto.MessageDto;
import vp.spring.rcs.web.dto.PageMessageDto;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@GetMapping("api/messages")
	public ResponseEntity<PageMessageDto> findAll(Pageable page){
		
		PageMessageDto dto = new PageMessageDto(messageService.findPage(page));
		return new ResponseEntity<>(dto, HttpStatus.OK);
		/* when groups exist: dto.content needs to be filtered, remove all msgs that have a group id - like so:
		List<MessageDto> filtered = dto.getContent().stream().filter(m -> m.getCategory().getId() == 2)
				.collect(Collectors.toList());
		dto.setContent(filtered);
		BUT
		 * then the total number of messages & pages still includes the filtered ones
		 * 	should the total number of elements then be manually changed too?
		 * AND
		 * there are empty spots => if size=1, page 0 shows 0 messages, but page=3 shows a message
		 * 	it follows the spots in array without 'closing' the holes
		*/
	
	}
	

	@GetMapping("api/messages/search")
	public ResponseEntity<PageMessageDto> findByTitle(@RequestParam String title, Pageable page){
		
		Page<Message> pageFound = messageService.findByTitle(title, page);
		PageMessageDto dto = new PageMessageDto(pageFound);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	@GetMapping("api/messages/{id}")
	public ResponseEntity<MessageDto> findById(@PathVariable long id) {
		
		Message found = messageService.findById(id);
		
		if (found!=null) {
			return new ResponseEntity<>(new MessageDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("api/messages/{id}")
	public ResponseEntity<MessageDto> deleteMessage(@PathVariable long id, @AuthenticationPrincipal User user) {
		
		// other way to validate user is:
		// String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Message found = messageService.findById(id);
		
		if (found!=null && found.getUser().getUsername().equalsIgnoreCase(user.getUsername())) { // do not ignore case, though
			found.setText("[deleted]");
			found = messageService.save(found);
			return new ResponseEntity<>(new MessageDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping("api/messages/{id}")  
	public ResponseEntity<MessageDto> editMessage(@PathVariable long id,
												@RequestBody MessageDto toEdit,
												@AuthenticationPrincipal User user){
		
		Message found = messageService.findById(id);
		
		if (found!=null && found.getUser().getUsername().equalsIgnoreCase(user.getUsername())) { // !! string comparison
		found.setId(id);
		found.setTitle(toEdit.getTitle());
		found.setText(toEdit.getText());
		found.setScore(toEdit.getScore());
		found.setCategory(categoryService.findById(toEdit.getCategory().getId()));
		found = messageService.save(found);
		return new ResponseEntity<>(new MessageDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	
	@PostMapping("api/messages/new") 
	public ResponseEntity<MessageDto> addMsg(@AuthenticationPrincipal User user,
											@RequestBody MessageDto message){
		
		SecurityUser creator = userService.findByUserName(user.getUsername()); // insecure?
		
		Message msg = new Message();
		msg.setTitle(message.getTitle());
		msg.setText(message.getText());
		msg.setCategory(categoryService.findById(message.getCategory().getId()));
		msg.setUser(creator);
		msg.setScore(1);
		msg = messageService.save(msg);
		
		return new ResponseEntity<>(new MessageDto(msg), HttpStatus.CREATED); 
	}
}
