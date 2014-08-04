package org.semmellitis.chesar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import reactor.spring.context.config.EnableReactor;

@Configuration
@Import({Persistence.class, EventConfig.class})
@ComponentScan("org.semmellitis")
@EnableReactor
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class,
    VelocityAutoConfiguration.class})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
