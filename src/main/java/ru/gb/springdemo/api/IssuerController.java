package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  @Autowired
  private IssuerService service;

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      log.info(e.getMessage());
      return ResponseEntity.notFound().build();
    } catch (BadRequestException e){
      log.info(e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

  @GetMapping(path = "/{issuerId}")
  @ResponseBody
  public ResponseEntity<Issue> issueInfo(@PathVariable("issuerId") long issuerId){
    log.info("Получен запрос на просмотр записи issueId = {}\"",issuerId);
    Issue findIssue;
    try {
      findIssue = service.issueInfo(issuerId);
      log.info("Запрос на просмотр записи issueId = {} о выдаче книги  успешно обработан\"",issuerId);

    } catch (NoSuchElementException e){
      log.info(e.getMessage());
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findIssue);
  }

}
