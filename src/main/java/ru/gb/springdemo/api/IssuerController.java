package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.BookRepository;
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

  @GetMapping(path = "/th/issues")
  public String issuesTableThymeleaf(Model model){
    log.info("Thymeleaf: Получен запрос на таблицу выдачи книг");
    List<Issue> issues = service.getIssues();
    log.info("Thymeleaf:{}", issues);
    Map<Issue,Book> map = new HashMap<>();
    for (int i = 0; i < issues.size(); i++) {
      map.put(issues.get(i),bookRepository.getBookById(issues.get(i).getBookId()));
    }
    log.info("Thymeleaf:{}", map);
    model.addAttribute("map",map);
    return "tableOfIssues";
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
    final Issue issue = service.issue(request);
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

  @GetMapping(path = "/{issuerId}")
  @ResponseBody
  public ResponseEntity<Issue> issueInfo(@PathVariable("issuerId") long issuerId){
    log.info("Получен запрос на просмотр записи issueId = {}\"",issuerId);
    Issue findIssue = service.issueInfo(issuerId);
    log.info("Запрос на просмотр записи issueId = {} о выдаче книги  успешно обработан\"",issuerId);
    return ResponseEntity.status(HttpStatus.OK).body(findIssue);
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
