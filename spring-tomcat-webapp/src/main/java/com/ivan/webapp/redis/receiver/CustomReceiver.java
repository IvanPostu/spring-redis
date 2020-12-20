package com.ivan.webapp.redis.receiver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomReceiver {
  private static final Logger logger = LogManager.getLogger(CustomReceiver.class);


  @Autowired
  public CustomReceiver() {
  }

  public void receiveMessage(String message) {
    logger.debug("Received <{}>", message);
  }
}