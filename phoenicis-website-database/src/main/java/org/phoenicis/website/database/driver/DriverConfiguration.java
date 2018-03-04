package org.phoenicis.website.database.driver;

import org.hibernate.SessionFactory;
import org.phoenicis.website.configuration.WebsiteGlobalConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import java.util.Properties;

@Configuration
@Import(WebsiteGlobalConfiguration.class)
public class DriverConfiguration {
    @Value("${phoenicis.website.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${phoenicis.website.hibernate.connection.driver_class}")
    private String hibernateConnectionDriverClass;

    @Value("${phoenicis.website.hibernate.connection.url}")
    private String hibernateConnectionUrl;

    @Value("${phoenicis.website.hibernate.connection.username}")
    private String hibernateConnectionUsername;

    @Value("${phoenicis.website.hibernate.connection.password}")
    private String hibernateConnectionPassword;

    @Value("${phoenicis.website.hibernate.connection.characterEncoding}")
    private String hibernateConnectionCharacterEncoding;

    @Value("${phoenicis.website.hibernate.connection.characterSet}")
    private String hibernateConnectionCharacterSet;

    @Value("${phoenicis.website.hibernate.connection.useUnicode}")
    private String hibernateConnectionUseUnicode;

    @Value("${phoenicis.website.hibernate.connection.sendStringParametersAsUnicode}")
    private String hibernateConnectionSendStringParametersAsUnicode;

    @Value("${phoenicis.website.hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Value("${phoenicis.website.hibernate.format_sql}")
    private String hibernateFormatSQL;

    @Value("${phoenicis.website.hibernate.show_sql}")
    private String hibernateShowSQL;

    @Value("${phoenicis.website.hibernate.entities.package}")
    private String domainScanPackage;

    @Bean
    @Lazy
    public Properties hibernateGlobalProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.connection.driver_class", hibernateConnectionDriverClass);
        properties.put("hibernate.connection.url", hibernateConnectionUrl);
        properties.put("hibernate.connection.username", hibernateConnectionUsername);
        properties.put("hibernate.connection.password", hibernateConnectionPassword);
        properties.put("hibernate.connection.characterEncoding", hibernateConnectionCharacterEncoding);
        properties.put("hibernate.connection.characterSet", hibernateConnectionCharacterSet);
        properties.put("hibernate.connection.useUnicode", hibernateConnectionUseUnicode);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.format_sql", hibernateFormatSQL);
        properties.put("hibernate.show_sql", hibernateShowSQL);
        properties.put("hibernate.connection.sendStringParametersAsUnicode", hibernateConnectionSendStringParametersAsUnicode);

        return properties;
    }

    @Bean
    @Lazy
    public EntitiesFinder entitiesFinder() {
        return new EntitiesFinder(domainScanPackage);
    }

    @Lazy
    @Bean
    HibernateSessionFactoryProvider hibernateSessionFactoryProvider() {
        return new HibernateSessionFactoryProvider(
                hibernateGlobalProperties(),
                entitiesFinder(),
                domainScanPackage
        );
    }

    @Lazy
    @Bean
    public SessionFactory sessionFactory() {
        return hibernateSessionFactoryProvider().getSessionFactory();
    }
}