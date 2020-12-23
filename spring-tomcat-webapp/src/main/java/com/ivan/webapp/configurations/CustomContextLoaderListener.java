package com.ivan.webapp.configurations;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

public class CustomContextLoaderListener extends ContextLoaderListener {
  private static final Logger logger = LogManager.getLogger(CustomContextLoaderListener.class);


  public CustomContextLoaderListener() {
    super();
  }

  @Override
	public void contextInitialized(ServletContextEvent event) {
    super.contextInitialized(event);
    logger.info(String.format("Context has been initialized. "));
  }
}