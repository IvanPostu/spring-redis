package com.ivan.webapp.configurations;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"production", "development"})
public class LoggerConfig {
  private static final Logger logger = LoggerFactory.getLogger(MyWebInitializer.class);
  
  @Value("${spring.profiles.active}")
  private String activeProfile;

  @PostConstruct
  private void postConstruct(){
    LoggerContext context = (LoggerContext)LogManager.getContext(false);

    if(activeProfile.equals("production")){
      context.setConfigLocation(URI.create("classpath:log4j2.production.xml"));
      context.reconfigure();
      logger.info("Log4j configuration file loaded for production");
    }

    if(activeProfile.equals("development")){
      context.setConfigLocation(URI.create("classpath:log4j2.development.xml"));
      context.reconfigure();
      logger.info("Log4j configuration file loaded for development");
    }

  }

}
