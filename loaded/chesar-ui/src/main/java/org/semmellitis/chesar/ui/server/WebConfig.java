package org.semmellitis.chesar.ui.server;


import org.semmellitis.chesar.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

@Configuration
@Import(Application.class)
public class WebConfig {

  public static void main(String[] args) {
    SpringApplication.run(WebConfig.class, args);
  }

  @Bean
  public HttpMessageConverters customConverters() {
    HttpMessageConverter<?> additional = new MappingJacksonHttpMessageConverter();
    return new HttpMessageConverters(additional);
  }
}
