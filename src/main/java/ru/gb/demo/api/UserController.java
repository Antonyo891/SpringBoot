package ru.gb.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.demo.model.Book;
import ru.gb.demo.model.User;
import ru.gb.demo.service.BookService;
import ru.gb.demo.service.UserService;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping(path = "entity/{bookId}")
//    @ResponseBody
//    public ResponseEntity<Book> bookInfo(@PathVariable("bookId") long bookId){
//        log.info("Получен запрос на описание книги bookId = {}",bookId);
//        Book findBook = bookService.bookInfo(bookId);
//        return ResponseEntity.status(HttpStatus.OK).body(findBook);
//    }
//
//    @GetMapping(path = "{bookId}")
//    public String bookInfoThymeleaf(@PathVariable("bookId") long bookId, Model model){
//        log.info("Thymeleaf: Получен запрос на описание книги bookId = {}",bookId);
//        Book findBook = bookService.bookInfo(bookId);
//        model.addAttribute("book",findBook);
//        return "book";
//    }

    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<List<User>> getUsers(){
        log.info(" Получен запрос пользователей");
        List<User> users = userService.getUsers();
        log.info("Пользователи :{}", users);
        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }

//    @DeleteMapping(path = "/delete/{bookId}")
//    @ResponseBody
//    public ResponseEntity<Book> deleteBook(@PathVariable("bookId") long bookId){
//        log.info("Получен запрос на удаление книги bookId = {}",bookId);
//        Book deleteBook = bookService.deleteBookById(bookId);
//        log.info("Книга {} удалена.",deleteBook);
//        return ResponseEntity.status(HttpStatus.ACCEPTED)
//                .header("Delete","Book with id "+bookId)
//                .body(deleteBook);
//    }
//    @PostMapping
//    public String addBook(@RequestBody Book book, Model model){
//        log.info("Получен запрос на добавление книги {}",book.getName());
//        Book addBook = bookService.addBook(book.getName());
//        model.addAttribute("book",addBook);
//        return  "book";
//    }
//    @PostMapping
//    public ResponseEntity<Book> addBook(@RequestBody Book book){
//        log.info("Получен запрос на добавление книги {}",book.getName());
//        Book addBook = bookService.addBook(book.getName());
//        return  ResponseEntity.status(HttpStatus.ACCEPTED)
//                .body(addBook);
//    }



}
