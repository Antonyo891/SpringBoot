package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book bookInfo(long bookId){
        if (bookRepository.getBookById(bookId)==null){
            throw new NoSuchElementException("Нет книги с идентифекатором \""+ bookId + "\"");
        }
        return bookRepository.getBookById(bookId);
    }
    public Book deleteBookById(long bookId) {
        Book tempBook = bookRepository.getBookById(bookId);
        if (tempBook == null)
            throw new NoSuchElementException("Нет книги с идентифекатором \""+ bookId + "\"");
        bookRepository.deleteBook(tempBook);
        return tempBook;
    }

    public Book addBook(String name){
       return bookRepository.addBook(new Book(name));
    }
}
