package org.semmellitis.chesar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import reactor.spring.context.config.EnableReactor;

@Configuration
@Import({Persistence.class, EventConfig.class})
@ComponentScan("org.semmellitis")
@EnableReactor
@EnableAutoConfiguration(exclude={BatchAutoConfiguration.class, MongoAutoConfiguration.class,
		ThymeleafAutoConfiguration.class,SolrAutoConfiguration.class})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
