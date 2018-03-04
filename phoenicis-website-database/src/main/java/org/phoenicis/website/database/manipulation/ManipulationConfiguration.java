package org.phoenicis.website.database.manipulation;

import org.phoenicis.website.database.driver.DriverConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@Import(DriverConfiguration.class)
public class ManipulationConfiguration {
    @Inject
    private DriverConfiguration driverConfiguration;

    @Bean
    public DatabaseManipulationService databaseManipulationService() {
        return new DatabaseManipulationService(driverConfiguration.sessionFactory());
    }
}
