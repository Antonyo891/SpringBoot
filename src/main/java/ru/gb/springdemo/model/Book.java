package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class Book {

  public static long sequence = 1L;

  private final long id;
  private final String name;

  public Book(String name) {
    this(sequence++, name);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Book book = (Book) object;
    return id == book.id && Objects.equals(name, book.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
