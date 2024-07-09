package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
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

    public Reader ReaderInfo(long readerId){
        if (readerRepository.getReaderById(readerId)==null){
            throw new NoSuchElementException("Нет читателя с идентифекатором \""+ readerId + "\"");
        }
        return readerRepository.getReaderById(readerId);
    }
    public Reader deleteReaderById(long bookId) {
        Reader tempReader = readerRepository.getReaderById(bookId);
        if (tempReader == null)
            throw new NoSuchElementException("Нет читателя с идентифекатором \""+ bookId + "\"");
        readerRepository.deleteReader(tempReader);
        return tempReader;
    }

    public Reader addReader(String name){
       return readerRepository.addReader(new Reader(name));
    }
    public List<Issue> allReadersIssue(long readerId){
        return issueRepository.getIssues().stream()
                .filter(i-> Objects.equals(i.getReaderId(),readerId))
                .collect(Collectors.toList());
    }
}
