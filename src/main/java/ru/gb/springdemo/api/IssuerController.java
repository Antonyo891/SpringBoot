package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.IssuerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/issue")
public class IssuerController {

  @Autowired
  private IssuerService service;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private ReaderRepository readerRepository;

    /**
     * Возврат книги в библиотеку
     * @param returnRequest - запрос на возврат книги
     * @param model
     * @return
     */
  @PutMapping
  public String returnBook(@RequestBody ReturnRequest returnRequest,Model model) {
    // найти в репозитории выдачу и проставить ей returned_at
    log.info("Получен запрос на возврат книги  bookId = {} issueId = {}",returnRequest.getBookId(),
            returnRequest.getIssueId());
    ReaderCard readerCard= service.returnBook(returnRequest);
    model.addAttribute("remainingBooks",readerCard.getRemainingBooks());
    model.addAttribute("readerName",readerCard.getReader().getName());
    return "readerCard";
  }

  @GetMapping(path = "/issues")
  public String issuesTableThymeleaf(Model model){
    log.info("Thymeleaf: Получен запрос на таблицу выдачи книг");
    List<Issue> issues = service.getIssues();
    log.info("Thymeleaf:{}", issues);
    Map<Issue, Book> map = new HashMap<>();
    for (int i = 0; i < issues.size(); i++) {
      map.put(issues.get(i),bookRepository.findById(issues.get(i).getBookId()).orElse(null));
    }
    log.info("Thymeleaf:{}", map);
    model.addAttribute("map",map);
    return "tableOfIssues";
  }

  @PostMapping
  @ResponseBody
  @Operation(summary = "запрос на выдачу книги", description = "формирует запись о выданной книге")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
    ;
    return ResponseEntity.status(HttpStatus.OK).body(service.issue(request));
  }

  @GetMapping(path = "/{issuerId}")
  public String issueInfo(@PathVariable("issuerId") long issuerId, Model model){
    log.info("Получен запрос на просмотр записи issueId = {}\"",issuerId);
    Issue issue = service.issueInfo(issuerId);
    model.addAttribute("issue",issue);
    model.addAttribute("book",bookRepository.findById(issue.getBookId()));
    model.addAttribute("reader",readerRepository.findById(issue.getReaderId()));
    log.info("Запрос на просмотр записи issueId = {} о выдаче книги  успешно обработан\"",issuerId);
    return "issue";
  }

  @GetMapping(path = "/th/{readerId}")
  public String readerCardInfo(@PathVariable("readerId") long readerId, Model model){
    log.info("Получен запрос на просмотр книг полученных readerId = {}\"",readerId);
    ReaderCard readerCard = service.getReaderCardById(readerId);
    model.addAttribute("remainingBooks",readerCard.getRemainingBooks());
    log.info("Th: remainingBooks = {}\"",readerCard.getRemainingBooks());
    model.addAttribute("readerName",readerCard.getReader().getName());
    return "readerCard";
  }

}
