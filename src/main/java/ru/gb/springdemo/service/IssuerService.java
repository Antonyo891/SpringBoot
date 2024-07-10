package ru.gb.springdemo.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssuerService {

  @Value("${application.max-allowed-books:1}")
  private long maxNumberOfTheBook;

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final ReaderService readerService;
  private final IssueRepository issueRepository;


  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    long numberOfReadersBook = issueRepository
            .getIssues()
            .stream()
            .filter(s-> Objects.equals(request.getReaderId(),s.getReaderId()))
            .count();
    if (numberOfReadersBook>=maxNumberOfTheBook){
      throw new ResponseStatusException(HttpStatus.CONFLICT,
              "Слишком много книг для читателя readerId = \"" +request.getReaderId() + "\"");
    }
    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }
  public Issue issueInfo(long issueId){
    Issue issue = issueRepository.getIssueById(issueId);
    if (issue==null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена запись о выдаче книги с идентификатором \"" + issueId + "\"");
    }
    return issue;
  }

  public ReaderCard returnBook(ReturnRequest returnRequest) {
    Issue issue = issueRepository.getIssueById(returnRequest.getIssueId());
    if (issue==null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена запись о выдаче книги с идентификатором \"" + returnRequest.getIssueId() + "\"");
    }
    if (issue.getTimeOfReturn()!=null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Книгу уже вернули \"" + returnRequest.getBookId() + "\"");
    issue.setTimeOfReturn(LocalDateTime.now().withNano(0));
    return getReaderCardById(issue.getReaderId());
  }

  public ReaderCard getReaderCardById(long readerId){
    Reader reader = readerService.ReaderInfo(readerId);
    ReaderCard readerCard = new ReaderCard();
    readerCard.setReader(reader);
    readerCard.setRemainingBooks(readerService.allReadersBook(readerId));
    return readerCard;
  }


  public List<Issue> getIssues() {
    return issueRepository.getIssues();
  }
}
