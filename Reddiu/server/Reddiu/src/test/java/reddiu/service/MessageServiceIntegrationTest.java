package reddiu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import reddiu.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MessageServiceIntegrationTest {
	
	@Autowired
	MessageService messageService;
	
	@Test
	public void testFindById() {
		
		Message found = messageService.findById(1);
		
		// N.B: won't work if this particular message/post is deleted
		
		assertNotNull(found);
		assertEquals(1, found.getId());
	}

}
