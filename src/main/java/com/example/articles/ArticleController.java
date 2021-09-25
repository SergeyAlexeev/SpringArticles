package com.example.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Date;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Integer addNewArticle(@RequestParam String title, @RequestParam String content, @RequestParam String date) {
        Article article = new Article();

        article.setTitle(title);
        article.setContent(content);
        article.setDate(Date.valueOf(date));

        articleRepository.save(article);

        return article.getId();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
