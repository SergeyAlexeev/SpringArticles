package com.example.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.OptionalValueBinding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Integer addNewArticle(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String date,
            @RequestParam Integer authorId
    ) {
        Article article = new Article();
        Author author = authorRepository.findById(authorId).orElse(null);

        article.setTitle(title);
        article.setContent(content);
        article.setDate(Date.valueOf(date));
        article.setAuthor(author);

        articleRepository.save(article);

        return article.getId();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
