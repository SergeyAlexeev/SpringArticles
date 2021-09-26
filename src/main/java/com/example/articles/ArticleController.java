package com.example.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Integer addNewArticle(
            @Valid @RequestParam String title,
            @Valid @RequestParam String content,
            @Valid @RequestParam String date,
            @Valid @RequestParam Integer authorId
    ) throws ParseException {
        Article article = new Article();
        Author author = authorRepository.findById(authorId).orElse(null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date upcomingDate = formatter.parse(date);

        article.setTitle(title);
        article.setContent(content);
        article.setDate(upcomingDate);
        article.setAuthor(author);

        articleRepository.save(article);

        return article.getId();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Article> getAllArticles(@PageableDefault(value = 10, page = 0) Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @GetMapping(path = "/stats")
    public @ResponseBody List<Stats> getStats() {
        return articleRepository.findArticleWeeklyStats();
    }
}
