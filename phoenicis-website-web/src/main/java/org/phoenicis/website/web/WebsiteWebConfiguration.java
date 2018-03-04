package org.phoenicis.website.web;

import com.phoenicis.website.apps.web.WebsiteAppsWebConfiguration;
import org.phoenicis.website.news.web.NewsWebConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@EnableWebMvc
@Configuration
@EnableSwagger2
@Import({
        WebsiteAppsWebConfiguration.class,
        NewsWebConfiguration.class
})

public class WebsiteWebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public Filter html5ModeUrlSupportFilter() {
        return new Html5ModeUrlSupportFilter();
    }

    @Bean
    public Docket plugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/api")
                .apiInfo(new ApiInfo(
                        "Phoenicis",
                        "Phoenicis website",
                        "1.0",
                        "",
                        "Quentin PÂRIS (qparis@phoenicis.org)",
                        " ",
                        ""
                ));
    }
}
