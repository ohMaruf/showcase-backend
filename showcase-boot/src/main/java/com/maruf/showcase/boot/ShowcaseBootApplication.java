package com.maruf.showcase.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.maruf.showcase")
@EnableJpaRepositories(basePackages = "com.maruf.showcase.persistence")
@EntityScan(basePackages = "com.maruf.showcase.domain")
public class ShowcaseBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShowcaseBootApplication.class, args);
  }
}
