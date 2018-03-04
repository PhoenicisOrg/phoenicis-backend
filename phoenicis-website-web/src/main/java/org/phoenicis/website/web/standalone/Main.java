package org.phoenicis.website.web.standalone;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final Map<String, String> modules = new HashMap<>();
        modules.put("/", "org.phoenicis.website.web");

        final StandaloneServer standaloneServer = new StandaloneServer(8004, modules);
        standaloneServer.run();
    }
}
