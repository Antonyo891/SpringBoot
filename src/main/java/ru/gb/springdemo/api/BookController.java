package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/{bookId}")
    @ResponseBody
    public ResponseEntity<Book> bookInfo(@PathVariable("bookId") long bookId){
        log.info("Получен запрос на описание книги bookId = {}",bookId);
        Book findBook = bookService.bookInfo(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(findBook);
    }

    @GetMapping(path = "/th/{bookId}")
    public String bookInfoThymeleaf(@PathVariable("bookId") long bookId, Model model){
        log.info("Thymeleaf: Получен запрос на описание книги bookId = {}",bookId);
        Book findBook = bookService.bookInfo(bookId);
        model.addAttribute("name",findBook.getName());
        model.addAttribute("bookId",findBook.getId());
        return "book";
    }

    @GetMapping(path = "/th/books/list")
    public String booksListThymeleaf(Model model){
        log.info("Thymeleaf: Получен запрос на список книг");
        List<Book> books = bookService.getBookRepository().getBooks();
        List<String> booksName = books.stream().map(Book::getName).toList();
        model.addAttribute("books",booksName);
        return "listOfBook";
    }
    @GetMapping(path = "/th/books/table")
    public String booksTableThymeleaf(Model model){
        log.info("Thymeleaf: Получен запрос на таблицу книг");
        List<Book> books = bookService.getBookRepository().getBooks();
        log.info("Thymeleaf:{}", books);
        model.addAttribute("books",books);
        return "tableOfBook";
    }

    @DeleteMapping(path = "/delete/{bookId}")
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable("bookId") long bookId){
        log.info("Получен запрос на удаление книги bookId = {}",bookId);
        Book deleteBook = bookService.deleteBookById(bookId);
        log.info("Книга {} удалена.",deleteBook);
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
