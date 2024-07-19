package ru.gb.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.demo.aspect.TimeLog;
import ru.gb.demo.model.Issue;
import ru.gb.demo.model.Reader;
import ru.gb.demo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping(path = "/{readerId}")
    public String readerInfo(@PathVariable("readerId") long readerId,Model model){
        log.info("Получен запрос на описание читателя readerId = {}",readerId);
        model.addAttribute("reader",readerService.ReaderInfo(readerId));
        return "reader";
    }
    @GetMapping(path = "/{readerId}/issue")
    @ResponseBody
    public ResponseEntity<List<Issue>> readersIssuesInfo(@PathVariable("readerId") long readerId){
        log.info("Получен запрос на просмотр выдачи книг читателю readerId = {}",readerId);
        List<Issue> readersIssues = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(readerService.allReadersIssue(readerId));
    }
    @GetMapping(path = "readers")
    public String readersTableThymeleaf(Model model){
        log.info("Thymeleaf: Получен запрос на таблицу читателей");
        List<Reader> readers = readerService.allReader();
        log.info("Thymeleaf:{}", readers);
        model.addAttribute("readers",readers);
        return "tableOfReaders";
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
    public String addReader(@RequestBody Reader reader, Model model){
        log.info("Получен запрос на добавление читателя {}",reader.getName());
        model.addAttribute("reader",readerService.addReader(reader));
        return  "reader";
    }

}
