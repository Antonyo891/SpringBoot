package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/{bookId}")
    @ResponseBody
    public ResponseEntity<Book> bookInfo(@PathVariable("bookId") long bookId){
        log.info("Получен запрос на описание книги bookId = {}",bookId);
        Book findBook;
        try {
            findBook = bookService.bookInfo(bookId);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findBook);
    }
    @DeleteMapping(path = "/delete/{bookId}")
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable("bookId") long bookId){
        log.info("Получен запрос на удаление книги bookId = {}",bookId);
        Book deleteBook;
        try {
            deleteBook = bookService.deleteBookById(bookId);
            log.info("Книга {} удалена.",deleteBook);
        } catch (NoSuchElementException e){
            log.info("Книга bookId = {} отсутствует в библиотеке.",bookId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Delete","Book with id "+bookId)
                .body(deleteBook);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        log.info("Получен запрос на добавленик книги {}",book.getName());
        Book addBook = bookService.addBook(book.getName());
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(addBook);
    }

}
