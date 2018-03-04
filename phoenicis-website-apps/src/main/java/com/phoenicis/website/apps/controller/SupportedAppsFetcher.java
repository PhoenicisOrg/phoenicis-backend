package com.phoenicis.website.apps.controller;

import org.apache.commons.io.IOUtils;
import org.phoenicis.repository.RepositoryManager;
import org.phoenicis.repository.dto.ApplicationDTO;
import org.phoenicis.repository.dto.CategoryDTO;
import org.phoenicis.repository.dto.TypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class SupportedAppsFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupportedAppsFetcher.class);
    private final RepositoryManager repositoryManager;
    private final List<CategoryDTO> categoryDTOs = new ArrayList<>();
    private final Map<String, byte[]> resourcesCache = new HashMap<>();

    public SupportedAppsFetcher(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        this.refreshRepository();
    }

    public List<CategoryDTO> fetchSupportedApps() {
        return Collections.unmodifiableList(categoryDTOs);
    }

    public byte[] fetchResource(String uuid) {
        final byte[] resourceFound = resourcesCache.get(uuid);
        return Arrays.copyOf(resourceFound, resourceFound.length);
    }

    private void refreshRepository() {
        repositoryManager.addCallbacks(repositoryDTO -> {
            final Optional<TypeDTO> applicationType = repositoryDTO.getTypes().stream().filter(typeDTO -> "Applications".equals(typeDTO.getName())).findFirst();
            applicationType.ifPresent(typeDTO -> {
                updateCache(typeDTO.getCategories());
            });
        }, e -> LOGGER.error("Error while downloading repository {}", e));
        repositoryManager.triggerRepositoryChange();
    }

    private void updateResourcesCache(List<CategoryDTO> downloadedCategories) throws URISyntaxException, IOException {
        this.resourcesCache.clear();
        final List<CategoryDTO> updatedCategories = new ArrayList<>();

        for (CategoryDTO categoryDTO : downloadedCategories) {
            updatedCategories.add(
                    new CategoryDTO.Builder()
                        .withApplications(updateResourcesCacheApplications(categoryDTO.getApplications()))
                        .withIcon(cacheResource(categoryDTO.getIcon()))
                        .withName(categoryDTO.getName())
                        .withType(categoryDTO.getType())
                        .build()
            );

        }

        categoryDTOs.clear();
        categoryDTOs.addAll(updatedCategories);
    }

    private List<ApplicationDTO> updateResourcesCacheApplications(List<ApplicationDTO> applications) throws IOException, URISyntaxException {
        final List<ApplicationDTO> updatedApplications = new ArrayList<>();

        for (ApplicationDTO application : applications) {
            updatedApplications.add(
                    new ApplicationDTO.Builder()
                            .withDescription(application.getDescription())
                            .withIcon(cacheResource(application.getIcon()))
                            .withMiniatures(application.getMiniatures().stream().map(this::cacheResource).collect(Collectors.toList()))
                            .withName(application.getName())
                            .withResources(application.getResources())
                            .withScripts(application.getScripts())
                            .build()
            );
        }

        return updatedApplications;
    }

    private URI cacheResource(URI resource) {
        try {
            final String resourceId = UUID.randomUUID().toString();
            if (resource != null) {
                resourcesCache.put(resourceId, IOUtils.toByteArray(resource));
            }

            return new URI("/api/v5.0/apps/resources/" + resourceId);
        } catch(IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }



    private void updateCache(List<CategoryDTO> categoryDTOS) {
        try {
            updateResourcesCache(categoryDTOS);
        } catch (URISyntaxException | IOException e) {
            throw new IllegalStateException(e);
        }
    }



}
