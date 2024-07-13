package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "book")
@RequiredArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 200, name = "name")
  private String name;


  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Book book = (Book) object;
    return Objects.equals(id, book.id) && Objects.equals(name, book.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
