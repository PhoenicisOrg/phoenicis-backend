package org.phoenicis.website.news.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.phoenicis.website.authentication.controller.UserDTO;

import java.util.Date;

public class NewsDTO {
    private final Long id;
    private final String title;
    private final String content;
    private final Date date;
    private final UserDTO author;
    private final String language;
    private final NewsCategoryDTO category;

    @JsonCreator
    public NewsDTO(@JsonProperty("id") Long id,
                   @JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("date") Date date,
                   @JsonProperty("author") UserDTO author,
                   @JsonProperty("language") String language,
                   @JsonProperty("category") NewsCategoryDTO category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.language = language;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public NewsCategoryDTO getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }
}
