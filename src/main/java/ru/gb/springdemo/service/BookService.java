package ru.gb.springdemo.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ReaderRepository readerRepository;

    public Book bookInfo(long bookId){
        if (bookRepository.findById(bookId).orElse(null)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет книги с идентифекатором \""+ bookId + "\"");
        }
        return bookRepository.findById(bookId).orElse(null);
    }
    public Book deleteBookById(long bookId) {
        Book tempBook = bookRepository.findById(bookId).orElse(null);
        if (tempBook == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет книги с идентифекатором \""+ bookId + "\"");
        bookRepository.delete(tempBook);
        return tempBook;
    }



    public Book addBook(String name){
        Book book= new Book();
        book.setName(name);
        bookRepository.save(book);
       return book;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
