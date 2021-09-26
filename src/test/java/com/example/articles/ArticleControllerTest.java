package com.example.articles;

import com.example.articles.controllers.ArticleController;
import com.example.articles.entities.Article;
import com.example.articles.entities.Author;
import com.example.articles.repositories.ArticleRepository;
import com.example.articles.repositories.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    ArticleRepository articleRepository;

    @MockBean
    AuthorRepository authorRepository;

    private Author createAuthor(String name, String secondName) {
        Author author = new Author();
        author.setName(name);
        author.setSecondName(secondName);

        return author;
    }

    private Article createArticle(String title, String content, Date date, Author author) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setDate(date);

        return article;
    }

    @Test
    public void getAllRecords_success() throws Exception {
        Article a1 = createArticle("a", "content 1", new Date(), createAuthor("Bob", "Doe"));
        Article a2 = createArticle("b", "content 2", new Date(), createAuthor("John", "Smith"));

        List<Article> records = new ArrayList<>(Arrays.asList(a1, a2));

        Mockito.when(articleRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/articles/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
