package com.ivan.webapp.controllers;

import java.util.Arrays;

import com.ivan.webapp.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private static final Logger logger = LogManager.getLogger(HomeController.class);

  
  @Value("${spring.profiles.active}")
  private String activeProfile;

  @Autowired
  private RedisTemplate<String, Object> redis;

  @RequestMapping(method = RequestMethod.GET, value = "/home")
  public ModelAndView home(){

    User u = new User();
    u.setFullName("Vasia Green");
    u.setId("22"); 
    redis.opsForList().rightPushAll(u.getId(),Arrays.asList(u));
 		logger.debug("This is logger debug");
    logger.info("This is logger info:");
		logger.warn("This is logger warn");
    logger.error("This is logger error");
    
    return new ModelAndView("home/home");
  }

}
