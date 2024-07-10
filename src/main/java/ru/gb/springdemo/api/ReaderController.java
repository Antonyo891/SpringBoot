package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping(path = "/{readerId}")
    @ResponseBody
    public ResponseEntity<Reader> readerInfo(@PathVariable("readerId") long readerId){
        log.info("Получен запрос на описание читателя readerId = {}",readerId);
        Reader findReader = readerService.ReaderInfo(readerId);
        return ResponseEntity.status(HttpStatus.OK).body(findReader);
    }
    @GetMapping(path = "/{readerId}/issue")
    @ResponseBody
    public ResponseEntity<List<Issue>> readersIssuesInfo(@PathVariable("readerId") long readerId){
        log.info("Получен запрос на просмотр выдачи книг читателю readerId = {}",readerId);
        List<Issue> readersIssues = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(readerService.allReadersIssue(readerId));
    }
    @DeleteMapping(path = "/delete/{readerId}")
    @ResponseBody
    public ResponseEntity<Reader> deleteReader(@PathVariable("readerId") long readerId){
        log.info("Получен запрос на удаление читателя readerId = {}",readerId);
        Reader deleteReader = readerService.deleteReaderById(readerId);
        log.info("Читатель {} удален.",deleteReader);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Delete","Reader with id "+readerId)
                .body(deleteReader);
    }
    @PostMapping
    public ResponseEntity<Reader> addReader(@RequestBody Reader reader){
        log.info("Получен запрос на добавление читателя {}",reader.getName());
        Reader addReader = readerService.addReader(reader.getName());
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(addReader);
    }

}
