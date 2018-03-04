package org.phoenicis.website.database.driver;

import java.io.IOException;
import java.util.Properties;

public class HibernateSessionFactoryProviderInMemory extends HibernateSessionFactoryProvider {

    public HibernateSessionFactoryProviderInMemory(String entitiesPackage) {
        super(new InMemoryProperties(), new EntitiesFinder(entitiesPackage), entitiesPackage);
    }

    private static class InMemoryProperties extends Properties {
        InMemoryProperties() {
            try {
                this.load(HibernateSessionFactoryProviderInMemory.class.getResourceAsStream("inmemory.properties"));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

