package ru.gb.springdemo.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }
  public Issue getIssueById(long issueId){
    return issues.stream()
            .filter(i-> Objects.equals(i.getId(),issueId))
            .findFirst()
            .orElse(null);
  }

}
