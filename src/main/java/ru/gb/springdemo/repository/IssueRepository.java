package ru.gb.springdemo.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository

public interface IssueRepository extends JpaRepository<Issue,Long> {
//    public List<Issue> findAllWithReader_id(Long readerId);
//    public List<Issue> findAllWithReaderIdAndTimeOfReturn(Long readerId, LocalDateTime timeOfReturn);
//    public Long countAllWithReaderIdAndTimeOfReturn(Long readerId, LocalDateTime timeOfReturn);


}
