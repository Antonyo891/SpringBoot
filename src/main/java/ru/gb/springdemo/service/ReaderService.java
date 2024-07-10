package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    public Reader ReaderInfo(long readerId){
        if (readerRepository.getReaderById(readerId)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет читателя с идентифекатором \""+ readerId + "\"");
        }
        return readerRepository.getReaderById(readerId);
    }
    public Reader deleteReaderById(long readerId) {
        Reader tempReader = readerRepository.getReaderById(readerId);
        if (tempReader == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет читателя с идентифекатором \""+ readerId + "\"");
        readerRepository.deleteReader(tempReader);
        return tempReader;
    }

    public Reader addReader(String name){
        if (name==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Имя не должно быть 'null'");
       return readerRepository.addReader(new Reader(name));
    }
    public List<Issue> allReadersIssue(long readerId){
        List<Issue> readersIssue= issueRepository.getIssues().stream()
                .filter(i-> Objects.equals(i.getReaderId(),readerId))
                .filter(i->i.getTimeOfReturn()==null)
                .collect(Collectors.toList());
        if (readersIssue==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Нет читателя с идентифекатором \""+ readerId + "\"");
        return readersIssue;
    }
    public List<Book> allReadersBook(long readerId){
        List<Issue> issues = allReadersIssue(readerId);
        return issues.stream()
                .map(Issue::getBookId)
                .map(bookRepository::getBookById)
                .toList();
    }

    public List<Reader> allReader() {
        return readerRepository.getReaders();
    }
}
