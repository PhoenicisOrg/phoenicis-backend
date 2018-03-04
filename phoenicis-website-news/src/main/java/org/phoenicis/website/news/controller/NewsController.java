package org.phoenicis.website.news.controller;

import org.phoenicis.website.authentication.controller.UserDTO;
import org.hibernate.query.Query;
import org.phoenicis.website.database.manipulation.DatabaseManipulationService;
import org.phoenicis.website.news.domain.News;
import org.phoenicis.website.news.domain.NewsCategory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewsController {
    private final DatabaseManipulationService databaseManipulationService;

    public NewsController(DatabaseManipulationService databaseManipulationService) {
        this.databaseManipulationService = databaseManipulationService;
    }

    public final List<NewsDTO> fetchAllNews() {
        return databaseManipulationService.executeInTransaction(session -> {
            final Query<News> query = session.createQuery("FROM News n WHERE n.category != :nullCategory ORDER BY n.id DESC", News.class);
            query.setParameter("nullCategory", session.load(NewsCategory.class, 0L));

            final List<News> news = query.list();

            return news.stream().map(this::toDTO).collect(Collectors.toList());
        });
    }

    private NewsDTO toDTO(News news) {
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                asDate(news.getDate(), news.getTime()),
                new UserDTO(news.getAuthor().getId(), news.getAuthor().getName()),
                news.getLanguage(),
                new NewsCategoryDTO(
                        news.getCategory().getId(),
                        news.getCategory().getTitle()
                )
        );
    }

    private Date asDate(String date, String time) {
        final DateFormat dateFormat = new SimpleDateFormat("u/d/M/Y H:m");
        try {
            return dateFormat.parse(date + " " + time);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
