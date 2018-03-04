package org.phoenicis.website.news.domain;

import org.phoenicis.website.authentication.domain.PlayOnLinuxUser;

import javax.persistence.*;

@Entity
@Table(name = "playonlinux_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10)")
    private Long id;

    @Column(name = "titre")
    private String title;

    @Column(name = "news", columnDefinition = "TEXT")
    private String content;

    @Column(name = "date")
    private String date;

    @Column(name = "heure")
    private String time;

    @ManyToOne
    @JoinColumn(name = "auteur", columnDefinition = "INT(10)")
    private PlayOnLinuxUser author;

    @Column(name = "lang")
    private String language;

    @ManyToOne
    @JoinColumn(name = "cat", columnDefinition = "INT(2)")
    private NewsCategory category;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PlayOnLinuxUser getAuthor() {
        return author;
    }

    public void setAuthor(PlayOnLinuxUser author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
