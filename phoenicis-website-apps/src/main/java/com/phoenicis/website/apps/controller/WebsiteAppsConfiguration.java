package com.phoenicis.website.apps.controller;

import org.phoenicis.multithreading.MultithreadingConfiguration;
import org.phoenicis.repository.RepositoryConfiguration;
import org.phoenicis.repository.RepositoryLocationLoader;
import org.phoenicis.tools.ToolsConfiguration;
import org.phoenicis.website.configuration.WebsiteGlobalConfiguration;
import org.phoenicis.win32.Win32Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

@Configuration
@Import({
        MultithreadingConfiguration.class,
        ToolsConfiguration.class,
        Win32Configuration.class,
        WebsiteGlobalConfiguration.class,
        RepositoryConfiguration.class
})
public class WebsiteAppsConfiguration {
    @Inject
    RepositoryConfiguration repositoryConfiguration;

    @Bean
    public SupportedAppsFetcher supportedAppsFetcher() {
        return new SupportedAppsFetcher(repositoryConfiguration.repositoryManager());
    }

    @Bean
    @Order(1)
    public RepositoryLocationLoader websiteRepositoryLoader() {
        return new WebsiteRepositoryLoader();
    }
}
