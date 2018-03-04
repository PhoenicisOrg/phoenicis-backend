package org.phoenicis.website.news.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.phoenicis.website.news.controller.NewsController;
import org.phoenicis.website.news.controller.NewsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v5.0/news")
@Api(value = "/v5.0/apps", description = "Apps API")
public class NewsWebController {
    private final NewsController newsController;

    public NewsWebController(NewsController newsController) {
        this.newsController = newsController;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "All news")
    public ResponseEntity<List<NewsDTO>> fetchSupportedApps() {
        return new ResponseEntity<>(newsController.fetchAllNews(), HttpStatus.OK);
    }

}
