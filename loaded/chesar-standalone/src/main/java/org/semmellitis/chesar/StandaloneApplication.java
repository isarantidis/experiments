package org.semmellitis.chesar;


import org.semmellitis.chesar.ui.server.WebConfig;
import org.springframework.boot.SpringApplication;

public class StandaloneApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebConfig.class, args);
  }

}
