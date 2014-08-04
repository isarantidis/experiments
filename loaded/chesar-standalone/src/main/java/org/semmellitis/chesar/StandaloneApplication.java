package org.semmellitis.chesar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import reactor.spring.context.config.EnableReactor;

public class StandaloneApplication {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
