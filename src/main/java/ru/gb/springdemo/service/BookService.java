package ru.gb.springdemo.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@Data
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book bookInfo(long bookId){
        if (bookRepository.getBookById(bookId)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет книги с идентифекатором \""+ bookId + "\"");
        }
        return bookRepository.getBookById(bookId);
    }
    public Book deleteBookById(long bookId) {
        Book tempBook = bookRepository.getBookById(bookId);
        if (tempBook == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет книги с идентифекатором \""+ bookId + "\"");
        bookRepository.deleteBook(tempBook);
        return tempBook;
    }

    public Book addBook(String name){
       return bookRepository.addBook(new Book(name));
    }
}
