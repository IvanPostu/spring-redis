package com.ivan.webapp.controllers;

import com.ivan.webapp.models.User;
import com.ivan.webapp.redis.sender.CustomSender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private static final Logger logger = LogManager.getLogger(HomeController.class);

  @Autowired
  private CustomSender customSender;

  @RequestMapping(method = RequestMethod.GET, value = "/home")
  public ModelAndView home(){


    User u = new User();
    u.setFullName("Vasia Green");
    u.setId("22"); 
    // redis.opsForList().rightPushAll(u.getId(),Arrays.asList(u));

    customSender.sendDataToRedisQueue("channel1", u);

    
    return new ModelAndView("home/home");
  }


}
