package com.ivan.webapp;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    long startTime = System.nanoTime();
    String webappDirLocation = "src/main/webapp/";
    Tomcat tomcat = new Tomcat();

    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) {
      webPort = "8080";
    }


    tomcat.setPort(Integer.valueOf(webPort));

    StandardContext ctx = (StandardContext) tomcat.addWebapp("/webapp", new File(webappDirLocation).getAbsolutePath());
    logger.info("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

    File additionWebInfClasses = new File("target/classes");
    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(
        new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
    ctx.setResources(resources);

    tomcat.start();

    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    logger.info(String.format("Tomcat server has been started in %d milliseconds.", 
      timeElapsed/1000000));

    tomcat.getServer().await();
  }
}