package com.literalura.service;

import com.literalura.model.Book;
import com.literalura.model.Author;
import com.literalura.repository.BookRepository;
import com.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book findAndSaveBookByTitle(String title) {
        Book existingBook = bookRepository.findByTitle(title);
        if (existingBook != null) return existingBook;

        String apiUrl = "https://gutendex.com/books?search=" + title;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCodeValue() == 200) {
            JSONObject json = new JSONObject(response.getBody());
            JSONArray results = json.getJSONArray("results");

            if (results.length() > 0) {
                JSONObject bookData = results.getJSONObject(0);
                String bookTitle = bookData.getString("title");
                String language = bookData.getJSONArray("languages").getString(0);
                int downloads = bookData.getInt("download_count");
                String authorName = bookData.getJSONArray("authors").getJSONObject(0).getString("name");

                Author author = authorRepository.findByName(authorName);
                if (author == null) {
                    author = new Author();
                    author.setName(authorName);
                    authorRepository.save(author);
                }

                Book book = new Book();
                book.setTitle(bookTitle);
                book.setLanguage(language);
                book.setDownloads(downloads);
                book.setAuthor(author);
                bookRepository.save(book);

                return book;
            }
        }
        return null;
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsAliveInYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
