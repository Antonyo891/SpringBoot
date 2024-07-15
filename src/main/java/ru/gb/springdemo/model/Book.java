package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "book")
@Schema(name = "Книга")
@RequiredArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "Идентификатор")
  private Long id;
  @Column(nullable = false, length = 200, name = "name")
  @Schema(name = "Название")
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
