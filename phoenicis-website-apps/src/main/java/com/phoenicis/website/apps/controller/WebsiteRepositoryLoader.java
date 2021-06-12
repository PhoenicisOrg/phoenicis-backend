package com.phoenicis.website.apps.controller;

import org.phoenicis.repository.RepositoryLocationLoader;
import org.phoenicis.repository.location.GitRepositoryLocation;
import org.phoenicis.repository.location.RepositoryLocation;
import org.phoenicis.repository.types.Repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WebsiteRepositoryLoader implements RepositoryLocationLoader {
    @Override
    public List<RepositoryLocation<? extends Repository>> loadRepositoryLocations() {
        try {
            return Collections.singletonList(
                    new GitRepositoryLocation.Builder()
                            .withBranch("master")
                            .withGitRepositoryUri(new URI("https://github.com/PhoenicisOrg/scripts"))
                            .build()
            );
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void saveRepositories(List<RepositoryLocation<? extends Repository>> list) {
        throw new UnsupportedOperationException();
    }
}
