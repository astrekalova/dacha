package de.unipotsdam.dacha.server;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DelegatingServletProxy extends GenericServlet {

	private static final long serialVersionUID = -5304240462170274464L;
	private static final Logger log = LoggerFactory.getLogger(DelegatingServletProxy.class);
	
	private Servlet delegate;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		delegate = applicationContext.getBean(getServletName(), Servlet.class);
		
		log.debug("Initilizing {} delegate", getServletName());
		
		delegate.init(config);
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		delegate.service(req, res);
	}
}
