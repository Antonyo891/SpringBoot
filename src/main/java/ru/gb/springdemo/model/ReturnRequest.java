package ru.gb.springdemo.model;

import lombok.Data;

/**
 * Запрос на возврат
 */
@Data
public class ReturnRequest {

  /**
   * Идентификатор записи о выдаче книги
   */
  private long issueId;

  /**
   * Идентификатор книги
   */
  private long bookId;

}
