package com.phoenicis.website.apps.web;

import com.phoenicis.website.apps.controller.SupportedAppsFetcher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.phoenicis.repository.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

@Controller
@RequestMapping("/v5.0/apps")
@Api(value = "/v5.0/apps", description = "Apps API")
public class WebsiteAppsWebController {
    private final SupportedAppsFetcher supportedAppsFetcher;

    public WebsiteAppsWebController(SupportedAppsFetcher supportedAppsFetcher) {
        this.supportedAppsFetcher = supportedAppsFetcher;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Supported apps")
    public ResponseEntity<List<CategoryDTO>> fetchSupportedApps() {
        return new ResponseEntity<>(supportedAppsFetcher.fetchSupportedApps(), HttpStatus.OK);
    }

    @RequestMapping(value = "resources/{uuid}", method = RequestMethod.GET)
    @ApiOperation(value = "Fetch a resource")
    public ResponseEntity<byte[]> fetchSupportedApps(HttpServletResponse httpServletResponse,
                                                     @PathVariable("uuid") @ApiParam("Resource UUID") String uuid) throws IOException {
        final byte[] resource = supportedAppsFetcher.fetchResource(uuid);
        try(InputStream resourceInputStream = new ByteArrayInputStream(resource)) {
            String contentType = URLConnection.guessContentTypeFromStream(resourceInputStream);
            httpServletResponse.setHeader("Content-type", contentType);
        }
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
