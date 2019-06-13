package vp.spring.rcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vp.spring.rcs.model.user.SecurityUser;

/*@Entity
@Table(catalog = "reddiu", name = "group")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	private SecurityUser creator;
	
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Message> messages = new HashSet<>();
	
	@ManyToMany
	private Set<SecurityUser> users = new HashSet<>();

	public Group() {
		super();
	}

	/*public Group(long id, String name, String description, SecurityUser creator, Set<Message> messages,
			Set<SecurityUser> users) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.messages = messages;
		this.users = users;
	}

	public Group(long id, String name, String description, SecurityUser creator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creator = creator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SecurityUser getCreator() {
		return creator;
	}

	public void setCreator(SecurityUser creator) {
		this.creator = creator;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<SecurityUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SecurityUser> users) {
		this.users = users;
	}
	
	

}*/
