package org.phoenicis.website.news.domain;

import javax.persistence.*;

@Entity
@Table(name = "playonlinux_news_cat")
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(2)")
    private Long id;

    @Column(name = "titre")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
