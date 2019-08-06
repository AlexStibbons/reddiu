package vp.spring.rcs.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.user.SecurityAuthority;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.security.TokenUtils;
import vp.spring.rcs.service.CommentService;
import vp.spring.rcs.service.MessageService;
import vp.spring.rcs.service.UserDetailsServiceImpl;
import vp.spring.rcs.web.dto.CommentDto;
import vp.spring.rcs.web.dto.LoginDTO;
import vp.spring.rcs.web.dto.MessageDto;
import vp.spring.rcs.web.dto.TokenDTO;
import vp.spring.rcs.web.dto.UserDTO;
import vp.spring.rcs.web.dto.UserProfileDto;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            return new ResponseEntity<TokenDTO>(new TokenDTO(genToken), 
            		HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        SecurityUser user = new SecurityUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        
        user = userDetailsService.register(user, userDTO.getRoles());
        
        if (user != null) {
        	return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED); 
        } else { // ako vec postoji korisnik sa tim korisnickim imenom
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }        
	}
	
	@GetMapping("api/roles")
	public ResponseEntity<List<String>> findRoles(){
		List<SecurityAuthority> found = userDetailsService.findAllAuthorities();
		
	/*	it wont work when there's a list of strings???*/
	  
	 List<String> authorities = found.stream()
									.map(SecurityAuthority::getName)
									.collect(Collectors.toList());
		return new ResponseEntity<>(authorities, HttpStatus.OK);
	}
	
	@GetMapping("api/user/{username}") // a string for pathVariable is not very usual
	public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable String username) {
		
		SecurityUser foundUser = userDetailsService.findByUserName(username);
		
		List<MessageDto> messages = messageService.findByUser(foundUser.getUsername()).stream()
									.map(MessageDto::new)
									.collect(Collectors.toList());
		
		List<CommentDto> comments = commentService.findByUser(foundUser.getUsername()).stream()
									.map(CommentDto::new)
									.collect(Collectors.toList());
		
		UserProfileDto userProfile = new UserProfileDto(foundUser.getUsername(), messages, comments);
		
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/api/shopping-cart", method = RequestMethod.GET)
	ResponseEntity<List<BookDto>> loadShoppingCart(){
		
		*** easy. but inellegant way:
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		***
		
		List<BookDto> retVal = cartService.findByUser(username).getBooks().stream()
								.map(BookDto::new)
								.collect(Collectors.toList());
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}*/


	
	
}
