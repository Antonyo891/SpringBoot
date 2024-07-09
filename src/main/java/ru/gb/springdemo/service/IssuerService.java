package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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


  public Issue issue(IssueRequest request) throws BadRequestException {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    long numberOfReadersBook = issueRepository
            .getIssues()
            .stream()
            .filter(s-> Objects.equals(request.getReaderId(),s.getReaderId()))
            .count();
    if (numberOfReadersBook>=maxNumberOfTheBook){
      throw new BadRequestException("Слишком много книг для читателя readerId = \"" +request.getReaderId() + "\"");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }
  public Issue issueInfo(long issueId){
    Issue issue = issueRepository.getIssueById(issueId);
    if (issue==null) {
      throw new NoSuchElementException("Не найдена запись о выдаче книги с идентификатором \"" + issueId + "\"");
    }
    return issue;
  }

}
