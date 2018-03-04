package org.phoenicis.website.web.standalone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

class StandaloneServer {
    private final int port;
    private final Map<String, String> contextModules;

    StandaloneServer(int port, Map<String, String> contextModules) {
        this.port = port;
        this.contextModules = contextModules;
    }

    void run() {
        try {
            final Server server = new Server(this.port);
            final HandlerCollection handlers = new HandlerCollection();

            for (Map.Entry<String, String> contextModule : this.contextModules.entrySet()) {
                handlers.addHandler(this.createContextFromWebXML(contextModule.getKey(), contextModule.getValue()));
            }

            server.setHandler(handlers);
            server.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WebAppContext createContextFromWebXML(String contextPath, String module) throws IOException, URISyntaxException {
        final WebAppContext webAppContext = new WebAppContext();
        final String modulePath = module.replace('.', '/');
        webAppContext.setContextPath(contextPath);
        webAppContext.setDescriptor(modulePath + "/WEB-INF/web.xml");

        final URL webAppDir = Thread.currentThread().getContextClassLoader().getResource(modulePath);
        if (webAppDir == null) {
            throw new FileNotFoundException(modulePath + "/WEB-INF/web.xml was not found");
        } else {
            webAppContext.setResourceBase(webAppDir.toURI().toString());
            webAppContext.setParentLoaderPriority(true);
            return webAppContext;
        }
    }
}