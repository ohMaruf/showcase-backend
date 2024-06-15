package com.maruf.showcase.persistence.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ShowcaseHibernateConfiguration.class)
public class ShowcasePersistenceConfiguration {

}
