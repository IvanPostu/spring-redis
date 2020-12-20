package com.ivan.webapp.redis.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class CustomSender {
  private static final Logger logger = LogManager.getLogger(CustomSender.class);


  @Autowired
  private RedisTemplate<String, Object> redis;

  public void sendDataToRedisQueue(String channel, Object input){
    redis.convertAndSend(channel, input);
    logger.debug("Sent: <{}>", input);
  }
}
