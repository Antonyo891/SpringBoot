package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "book_id",nullable = false)
  private long bookId;
  @Column(name = "reader_id",nullable = false)
  private long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "time_of_receipt",nullable = false)
  private LocalDateTime timeOfReceipt;
  @Column(name = "time_of_return")
  private LocalDateTime timeOfReturn;


}
