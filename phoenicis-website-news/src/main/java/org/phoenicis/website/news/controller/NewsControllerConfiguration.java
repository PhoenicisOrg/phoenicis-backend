package org.phoenicis.website.news.controller;

import org.phoenicis.website.database.manipulation.ManipulationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
@Import(ManipulationConfiguration.class)
public class NewsControllerConfiguration {
    @Inject
    private ManipulationConfiguration manipulationConfiguration;

    @Bean
    public NewsController newsController() {
        return new NewsController(manipulationConfiguration.databaseManipulationService());
    }
}
