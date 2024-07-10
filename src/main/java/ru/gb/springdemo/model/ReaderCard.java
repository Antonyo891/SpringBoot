package ru.gb.springdemo.model;

import lombok.Data;

import java.util.List;

/**
 * Запрос на выдачу
 */
@Data
public class ReaderCard {

  /**
   * Идентификатор читателя
   */
  private Reader reader;

  /**
   * Идентификаторы оставшихся книг
   */
  private List<Book> remainingBooks;

}
