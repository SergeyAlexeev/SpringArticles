package com.example.articles;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
    @Query(
        value = "SELECT a.date as date, COUNT(a.date) AS count FROM article AS a WHERE a.date IS NOT NULL GROUP BY a.date ORDER BY a.date DESC LIMIT 7",
        nativeQuery = true
    )
    List<Stats> findArticleWeeklyStats();
}

