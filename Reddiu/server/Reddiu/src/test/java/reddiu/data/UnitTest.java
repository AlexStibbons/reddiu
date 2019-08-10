package reddiu.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import reddiu.model.Message;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitTest {

	// schema reddiu not found??
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	MessageRepository messageRepository;
	
	/*@Before
	public void setup() {
		em.persist(new Message());
		em.persist(new Message());
		em.persist(new Message());
	}*/
	
	@Test
	public void testFindAll() {
		em.persist(new Message());
		em.persist(new Message());
		em.persist(new Message());

		List<Message> found = messageRepository.findAll();
		
		assertEquals(3, found.size());
	}

}
