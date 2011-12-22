package de.unipotsdam.dacha.test;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration({"classpath*:application-context.xml"})
public abstract class TransactionAwareTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected SessionFactory sessionFactory;
}
