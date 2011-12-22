package de.unipotsdam.dacha.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.ContextAwareTest;

public class ChatEngineImplTest extends ContextAwareTest  {

	@Autowired
	private ChatEngine chatEngine;
	
	@Test
	public void testGetResponse() {
		
		Response response = chatEngine.getResponse("How are you?");
		assertNotNull(response);
	}
	
	
	@Test
	public void testGetResponseForNonExistingRequest() {
		
		Response response = chatEngine.getResponse("sfdg");
		assertNotNull(response);
	}

}
