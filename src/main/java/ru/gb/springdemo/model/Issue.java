package ru.gb.springdemo.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
// @Entity
public class Issue {

  public static long sequence = 1L;

  private final long id;
  private final long bookId;
  private final long readerId;

  /**
   * Дата выдачи
   */
  private final LocalDateTime timeOfReceipt;
  private LocalDateTime timeOfReturn;

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.timeOfReceipt = LocalDateTime.now().withNano(0);
    this.timeOfReturn = null;
  }

}
