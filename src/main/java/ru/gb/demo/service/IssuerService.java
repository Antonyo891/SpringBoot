package ru.gb.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.demo.ReaderProperties;
import ru.gb.demo.aspect.TimeLog;
import ru.gb.demo.model.*;
import ru.gb.demo.repository.BookRepository;
import ru.gb.demo.repository.IssueRepository;
import ru.gb.demo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(ReaderProperties.class)
public class IssuerService {

//  @Value("${application.max-allowed-books:2}")
//  private int maxNumberOfTheBook;

  // спринг это все заинжектит
  @Autowired
  private final BookRepository bookRepository;
  @Autowired
  private final ReaderRepository readerRepository;
  @Autowired
  private final ReaderService readerService;
  @Autowired
  private final IssueRepository issueRepository;
  @Autowired
  private ReaderProperties readerProperties;

  @TimeLog
  public Issue issue(IssueRequest request) {
    Book book = bookRepository.findById(request.getBookId()).orElse(null);
    if (book == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    Reader reader = readerRepository.findById(request.getReaderId()).orElse(null);
    if (reader == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    log.info("Читетелем {} запрошена книга {}",reader.getName(),book.getName());
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    long numberOfReadersBook = issueRepository.findAll().stream()
            .filter(i->(i.getTimeOfReturn()==null &&
                    Objects.equals(i.getReaderId(),request.getReaderId()))).count();
    log.info("Количество книг на руках = {}",numberOfReadersBook);
    if (numberOfReadersBook>=readerProperties.getMaxAllowedBooks()){
      throw new ResponseStatusException(HttpStatus.CONFLICT,
              "Слишком много книг (" + numberOfReadersBook +
                      ") для читателя readerId = \"" +request.getReaderId() + "\"");
    }
    log.info("Можно выдавать");
    Issue issue = new Issue();
    issue.setReaderId(request.getReaderId());
    issue.setBookId(request.getBookId());
    issue.setTimeOfReceipt(LocalDateTime.now().withNano(0));
    issueRepository.save(issue);
    return issue;
  }
  public Issue issueInfo(long issueId){
    Issue issue = issueRepository.findById(issueId).orElse(null);
    if (issue==null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена запись о выдаче книги с идентификатором \"" + issueId + "\"");
    }
    return issue;
  }

  public ReaderCard returnBook(ReturnRequest returnRequest) {
    Issue issue = issueRepository.findById(returnRequest.getIssueId()).orElse(null);
    if (issue==null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Не найдена запись о выдаче книги с идентификатором \"" + returnRequest.getIssueId() + "\"");
    }
    if (issue.getTimeOfReturn()!=null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Книгу уже вернули \"" + returnRequest.getBookId() + "\"");
    issue.setTimeOfReturn(LocalDateTime.now().withNano(0));
    issueRepository.flush();
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
    return issueRepository.findAll();
  }
}
