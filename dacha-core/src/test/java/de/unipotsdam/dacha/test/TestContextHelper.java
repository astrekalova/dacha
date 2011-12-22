package de.unipotsdam.dacha.test;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

@Component
public class TestContextHelper implements BeanFactoryAware, InitializingBean {

	private ConfigurableBeanFactory configurableBeanFactory;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		configurableBeanFactory.registerScope("session", new SimpleThreadScope());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (beanFactory instanceof ConfigurableBeanFactory) {
			configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
		}
	}
}
