package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.model.ReaderCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Data
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new ru.gb.springdemo.model.Reader("Игорь"),
            new Reader("Денис"),
            new Reader("Ольга")
    ));
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }
  public void deleteReader(Reader reader) {
    readers.remove(reader);
  }
  public Reader addReader(Reader reader){
    readers.add(reader);
    return readers.getLast();
  }

}
