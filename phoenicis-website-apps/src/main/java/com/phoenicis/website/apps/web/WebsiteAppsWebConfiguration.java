package com.phoenicis.website.apps.web;

import com.phoenicis.website.apps.controller.WebsiteAppsConfiguration;
import org.phoenicis.configuration.PhoenicisGlobalConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@Import({
        WebsiteAppsConfiguration.class
})
public class WebsiteAppsWebConfiguration {
    @Inject
    WebsiteAppsConfiguration websiteAppsConfiguration;

    @Bean
    public WebsiteAppsWebController websiteAppsWebController() {
        return new WebsiteAppsWebController(websiteAppsConfiguration.supportedAppsFetcher());
    }
}
