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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    public Reader ReaderInfo(long readerId){
        if (readerRepository.findById(readerId).orElse(null)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет читателя с идентифекатором \""+ readerId + "\"");
        }
        return readerRepository.findById(readerId).orElse(null);
    }
    public Reader deleteReaderById(long readerId) {
        Reader tempReader = readerRepository.findById(readerId).orElse(null);
        if (tempReader == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет читателя с идентифекатором \""+ readerId + "\"");
        readerRepository.delete(tempReader);
        return tempReader;
    }

    public Reader addReader(Reader reader){
        if ((reader==null)||(reader.getName()==null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Имя не должно быть 'null'");
        readerRepository.save(reader);
        return reader;
    }
    public List<Issue> allReadersIssue(long readerId){
        if (readerRepository.findById(readerId).orElse(null)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет читателя с идентифекатором \""+ readerId + "\"");
        List<Issue> readersIssue = issueRepository.findAll().stream()
                .filter(i->(i.getTimeOfReturn()==null &&
                        Objects.equals(i.getReaderId(),readerId))).toList();
        if ((readersIssue==null)||(readersIssue.isEmpty())) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "У читателя нет книг на руках");
        return readersIssue;
    }
    public List<Book> allReadersBook(long readerId){
        List<Long> bookId = allReadersIssue(readerId).stream()
                .map(Issue::getBookId).toList();
        return bookId.stream().map(s->bookRepository.findById(s).orElse(null)).toList();
    }

    public List<Reader> allReader() {
        return readerRepository.findAll();
    }
}
