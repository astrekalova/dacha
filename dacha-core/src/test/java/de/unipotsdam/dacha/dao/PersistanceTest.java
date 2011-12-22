package de.unipotsdam.dacha.dao;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.TransactionAwareTest;


public class PersistanceTest extends TransactionAwareTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void dataSource() {
		assertNotNull(dataSource);
	}
	
	@Test
	public void user() {

		assertNotNull(userDao);
		
		User user = new User();
		user.setName("username");
		user.setNr(12);
		
		userDao.saveOrUpdate(user);
		
		assertNotNull(user.getId());
	}
}
