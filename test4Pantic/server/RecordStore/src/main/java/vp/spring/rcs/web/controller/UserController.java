package vp.spring.rcs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.security.TokenUtils;
import vp.spring.rcs.web.dto.LoginDTO;
import vp.spring.rcs.web.dto.TokenDTO;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
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
	
	/*
	Page<Film> films = filmserive.find();
	List<FilmDto> filmDto = films.getContent().stream()
							.map(FilmDto::new)
							.collect(Collectors.toList());
	Page<FilmDto> dto = new PageImpl<FilmDTO>(filmDto, page, films.getTotalElements());

	frontend: 
	- does not need a model og a Page object
	- enough to say private page: any;
	- then take any data from page
	- <button [disabled]="page.first" (click)="prevPage()"> Previous page </button>
	- test [disables]="!page.hasPrevious"
	*/

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

	/*// Administrator vrsi registraciju. Mora da postoji token u zahtevu upucenog ka /api/register	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}*/
}
