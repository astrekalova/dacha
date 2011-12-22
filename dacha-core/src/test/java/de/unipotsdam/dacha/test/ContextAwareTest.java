package de.unipotsdam.dacha.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration({"classpath*:application-context.xml"})
public abstract class ContextAwareTest extends AbstractJUnit4SpringContextTests {

}
