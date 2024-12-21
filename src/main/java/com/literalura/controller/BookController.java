package com.literalura.controller;

import com.literalura.model.Book;
import com.literalura.model.Author;
import com.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public Book findAndSaveBook(@RequestParam String title) {
        return bookService.findAndSaveBookByTitle(title);
    }

    @GetMapping("/books")
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/authors")
    public List<Author> listAllAuthors() {
        return bookService.listAllAuthors();
    }

    @GetMapping("/authors/alive")
    public List<Author> listAuthorsAliveInYear(@RequestParam int year) {
        return bookService.listAuthorsAliveInYear(year);
    }

    @GetMapping("/books/language")
    public List<Book> listBooksByLanguage(@RequestParam String language) {
        return bookService.listBooksByLanguage(language);
    }
}
