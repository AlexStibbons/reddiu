package reddiu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import reddiu.data.AuthorityRepository;
import reddiu.data.UserRepository;
import reddiu.model.user.SecurityAuthority;
import reddiu.model.user.SecurityUser;
import reddiu.model.user.SecurityUserAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  AuthorityRepository authorityRepository;
  
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	SecurityUser user = userRepository.findByUsername(username);
	if (user == null) {
	  throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	} else {
		/*List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (UserAuthority ua: user.getUserAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(ua.getAuthority().getName()));
		}*/
		
		//Java 1.8 way   	
		List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
	            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
	            .collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(
			  user.getUsername(),
			  user.getPassword(),
			  grantedAuthorities);
	}
  }
  
  public SecurityUser register(SecurityUser securityUser, List<String> roles) {
		if (userRepository.findByUsername(securityUser.getUsername()) != null) {
			return null; // ako vec postoji korisnik sa tim korisnickim imenom
		} else {
			// objekat korisnik vezemo sa ulogama

			// uloge su predstavljene objektom SecurityAuthority
			// za svaku ulogu, nadjemo odgovarajuci SecurityAuthority objekat
			for (String role : roles) {
				SecurityAuthority authority = authorityRepository
						.findByName(role);

				// povezemo authority sa korisnikom preko objekta
				// SecurityUserAUthority
				SecurityUserAuthority sua = new SecurityUserAuthority();
				sua.setAuthority(authority);
				sua.setUser(securityUser);
				// dodamo vezni objekat u korisnikovu listu koju ce biti
				// kaskadno perzistirana
				// zajedno sa korisnikom
				securityUser.getUserAuthorities().add(sua);
			}
			// enkodujemo sifru pre snimanja u bazu
			securityUser.setPassword(passwordEncoder.encode(securityUser.getPassword()));
			
			return userRepository.save(securityUser); // perzistira se korisnik
		}
  }
  
  public SecurityUser findByUserName(String username) {
	  return userRepository.findByUsername(username);
  }
  
  public List<SecurityAuthority> findAllAuthorities() {
	  return authorityRepository.findAll();
  }
  

 
  
}
