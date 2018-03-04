package org.phoenicis.website.news.web;

import org.phoenicis.website.news.controller.NewsControllerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;
import javax.persistence.Column;

@Configuration
@Import(NewsControllerConfiguration.class)
public class NewsWebConfiguration {
    @Inject
    private NewsControllerConfiguration newsControllerConfiguration;

    @Bean
    public NewsWebController newsWebController() {
        return new NewsWebController(newsControllerConfiguration.newsController());
    }
}
