package org.phoenicis.website.database.driver;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.Iterator;

/**
 * Finds all the qualified name of the classes corresponding to database entities
 * Implements {@link Iterable}
 *
 * @see Entity
 * @see Iterable
 */
public class EntitiesFinder implements Iterable<String> {
    private final ClassPathScanningCandidateComponentProvider scanner;
    private final String entitiesPackage;

    EntitiesFinder(String entitiesPackage) {
        this.entitiesPackage = entitiesPackage;
        scanner = new ClassPathScanningCandidateComponentProvider(true);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
    }

    @Override
    public Iterator<String> iterator() {
        return scanner.findCandidateComponents(entitiesPackage).stream().map(BeanDefinition::getBeanClassName).iterator();
    }
}