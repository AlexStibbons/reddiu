package reddiu.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import reddiu.data.MessageRepository;
import reddiu.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessageServiceUnitTest {
	
	@Autowired
	MessageService messageService;
	
	@MockBean
	MessageRepository messageRepository;
	
	@Before
	public void setUp() {
		
		given(
				messageRepository.findById(0L)
		).willReturn(
				Optional.of(new Message())
		);
		
	}
	
	@Test
	public void testFindById() {
		Message found = messageService.findById(0);
		
		assertNotNull(found);
	}
	
	

}
