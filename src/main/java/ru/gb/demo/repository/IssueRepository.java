package ru.gb.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.demo.model.Issue;

@Repository

public interface IssueRepository extends JpaRepository<Issue,Long> {
//    public List<Issue> findAllWithReader_id(Long readerId);
//    public List<Issue> findAllWithReaderIdAndTimeOfReturn(Long readerId, LocalDateTime timeOfReturn);
//    public Long countAllWithReaderIdAndTimeOfReturn(Long readerId, LocalDateTime timeOfReturn);


}
