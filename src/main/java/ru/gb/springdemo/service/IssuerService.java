package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssuerService {

  @Value("${application.max-allowed-books:1}")
  private long maxNumberOfTheBook;

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
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

}
