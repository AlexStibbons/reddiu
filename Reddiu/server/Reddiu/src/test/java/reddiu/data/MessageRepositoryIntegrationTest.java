package reddiu.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import reddiu.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MessageRepositoryIntegrationTest {
	
	@Autowired
	MessageRepository messageRepository;
	
	@Test
	public void testFindAll() {
		List<Message> found = messageRepository.findAll();
		
		assertNotNull(found.get(0));
		assertEquals(1, found.get(0).getId()); // will fail if that message/post is deleted
	}

}
