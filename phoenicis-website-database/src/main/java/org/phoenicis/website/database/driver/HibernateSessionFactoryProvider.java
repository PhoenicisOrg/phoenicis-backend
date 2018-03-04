package org.phoenicis.website.database.driver;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

class HibernateSessionFactoryProvider implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryProvider.class);
    private final SessionFactory sessionFactory;
    private final Iterable<String> entities;

    HibernateSessionFactoryProvider(Properties globalProperties,
                                    Iterable<String> entities,
                                    String domainScanPackage) {
        this.entities = entities;

        final Configuration configuration = createConfiguration()
                .addPackage(domainScanPackage)
                .addProperties(globalProperties);

        addAnnotatedEntities(configuration);

        LOGGER.info("Loading configuration: " + globalProperties.toString());
        this.sessionFactory = configuration.buildSessionFactory();
    }

    private Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Register domain classes here
     *
     * @param configuration Uses {@link Configuration#addAnnotatedClass}
     */
    private void addAnnotatedEntities(Configuration configuration) {
        for (String entityClassName : entities) {
            try {
                LOGGER.info("Found: " + entityClassName);
                configuration.addAnnotatedClass(Class.forName(entityClassName));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Provides a {@link SessionFactory}
     *
     * @return a Hibernate Session Factory
     * @see SessionFactory
     */
    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}