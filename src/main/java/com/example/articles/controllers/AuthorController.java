package com.example.articles.controllers;

import com.example.articles.entities.Author;
import com.example.articles.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Integer addNewAuthor(@RequestParam String name, @RequestParam String secondName) {
        Author author = new Author();

        author.setName(name);
        author.setSecondName(secondName);

        authorRepository.save(author);

        return author.getId();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}