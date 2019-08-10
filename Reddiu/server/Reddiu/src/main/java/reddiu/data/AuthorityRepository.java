package reddiu.data;

import org.springframework.data.jpa.repository.JpaRepository;

import reddiu.model.user.SecurityAuthority;



public interface AuthorityRepository extends JpaRepository<SecurityAuthority, Long> {
	SecurityAuthority findByName(String name);
	
}
