package com.maruf.showcase.persistence.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.maruf.showcase.domain.model")
@EnableJpaRepositories(basePackages = "com.maruf.showcase.persistence.repositories")
public class ShowcaseHibernateConfiguration {

  @Bean(name = "transactionManager")
  public PlatformTransactionManager platformTransactionManager() {
    return new JpaTransactionManager();
  }

}
