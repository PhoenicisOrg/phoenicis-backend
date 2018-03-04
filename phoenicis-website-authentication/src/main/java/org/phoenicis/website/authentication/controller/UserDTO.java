package org.phoenicis.website.authentication.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    private final Long id;
    private final String name;

    @JsonCreator
    public UserDTO(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
