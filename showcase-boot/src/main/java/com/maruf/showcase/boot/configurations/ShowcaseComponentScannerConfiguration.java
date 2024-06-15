package com.maruf.showcase.boot.configurations;

import com.maruf.showcase.businesslogic.configurations.ShowcaseBusinessLogicConfiguration;
import com.maruf.showcase.persistence.configurations.ShowcasePersistenceConfiguration;
import com.maruf.showcase.web.configurations.ShowcaseWebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    ShowcasePersistenceConfiguration.class,
    ShowcaseBusinessLogicConfiguration.class,
    ShowcaseWebConfiguration.class
})
public class ShowcaseComponentScannerConfiguration {

}
