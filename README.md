В силу слившихся обстоятедьств я не смог сдать вовремя основную часть домашних работ...
Здесь все работы которые успел сделать...остальные (security и test) буду попозже делать...закончились силы)))Но все сделаю
Надеюсь этого хватит чтобы засчитать освоение программы курса.
Заранее спасибо.
<details>
  <summary> [3_семинар_дз](https://github.com/Antonyo891/SpringBoot.git) :</summary> <br> 
   + 0. Переварить все, что было изучано.
   + 1. Доделать сервис управления книгами:
    +1.1 Реализовать контроллер по управлению книгами с ручками: GET /book/{id} - получить описание книги, DELETE /book/{id} - удалить книгу, POST /book - создать книгу
   + 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
   + 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи
   *
   + 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг. Если есть - не выдавать книгу (статус ответа - 409 Conflict)
   + 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
   -
   - 3.1* В Issue поле timestamp разбить на 2: issued_at, returned_at - дата выдачи и дата возврата
   - 3.2* К ресурс POST /issue добавить запрос PUT /issue/{issueId}, который закрывает факт выдачи. (т.е. проставляет returned_at в Issue).
   - Замечание: возвращенные книги НЕ нужно учитывать при 2.1
   + 3.3** Пункт 2.1 расширить параметром, сколько книг может быть на руках у пользователя.
   * Должно задаваться в конфигурации (параметр application.issue.max-allowed-books). Если параметр не задан - то использовать значение 1.
</details>
<details>
  <summary>[4 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_four):</summary> <br> 
  /**
   * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
   * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
   * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
   * 1.2 /ui/reader - аналогично 1.1
   * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
   * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
   *
   * Чтобы шаблонизатор thymeleaf заработал, то нужно добавить зависимость в pom.xml:
   *        <dependency>
   *            <groupId>org.springframework.boot</groupId>
   *            <artifactId>spring-boot-starter-thymeleaf</artifactId>
   *        </dependency>
   */
  </details>

  <details>
  <summary>[5 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_five):</summary> <br> 
/**

Домашнее задание:
Подключить базу данных к проекту "юиблиотека", который разработан на прошлых уроках.
1.1 Подключить spring-boot-starter-data (и необходимый драйвер) и указать параметры соединения в application.yml
1.2 Для книги, читателя и факта выдачи описать JPA-сущности
1.3 Заменить самописные репозитории на JPA-репозитории
Замечание: базу данных можно использовать любую (h2, mysql, postgress). */
   </details><details>
  <summary>[6 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_six):</summary> <br> 
/**
 * Домашнее задание:
 * 1. Подключить openApi3 и swager.
 * 2. Описать эндпоинты и возвращаемые тела.
 * Скрин результата
 * ![results](https://github.com/Antonyo891/SpringBootLesson3/blob/seminar_six/src/main/resources/Swager.png)
 */
   </details><details>
  <summary>[8 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_eight):</summary> <br> 
8 семинар дз: 1. [Создать аннотацию замера времени исполнения метода (Timer)](https://github.com/Antonyo891/SpringBoot/blob/seminar_eight/src/main/java/ru/gb/demo/aspect/TimeLogAspect.java). Она может ставиться над методами или классами. Аннотация работает так: после исполнения метода (метода класса) с такой аннотацией, необходимо в лог записать следующее: className - methodName #(количество секунд выполнения)

2.* Создать аннотацию RecoverException, которую можно ставить только над методами. 
@interface RecoverException {
Class<? extends RuntimeException>[] noRecoverFor() default {};
}
У аннотации должен быть параметр noRecoverFor, в котором можно перечислить другие классы исключений. Аннотация работает так: если во время исполнения метода был экспешн, то не прокидывать его выше и возвращать из метода значение по умолчанию (null, 0, false, ...). При этом, если тип исключения входит в список перечисленных в noRecoverFor исключений, то исключение НЕ прерывается и прокидывается выше. 3.*** Параметр noRecoverFor должен учитывать иерархию исключений.

Сдавать ссылкой на файл-аспект в проекте на гитхабе.
   </details>
   <details>
  <summary>
    [9 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_eleven)
  </summary> <br> 
  1.Восстановить пример, рассмотренный на уроке (запустить эврику и 2 сервиса; заставить их взаимодействовать)
Сдать скриншот страницы /eureka/apps с зарегистрированными приложениями.
На скрине должно быть видно оба сервиса (book-service, issuer-service) <br>
  
![screen](https://github.com/Antonyo891/SpringBoot/blob/seminar_eleven/src/main/resources/Eureka.png)
</details><details>
  <summary>[11 семинар дз](https://github.com/Antonyo891/SpringBoot/tree/seminar_eleven):</summary> <br> 
  Проблематика: имеется несколько микросервисов (проектов) на spring-boot: reader-service, book-service, issue-service, ...
Хочется, чтобы в каждом из этих проектов работал аспект-таймер, замеряющий время выполнения метода бина, помеченного аннотацией @Timer (см. дз к уроку 8)

Решение: создать стартер, который будет инкапсулировать в себе аспект и его автоматический импорт в подключающий проект.
То есть:
1. [Пишем стартер, в котором задекларирован аспект и его работа](https://github.com/Antonyo891/SpringBoot/tree/seminar_eleven/starter)
2. Подключаем стартер в [reader-service](https://github.com/Antonyo891/SpringBoot/blob/seminar_eleven/readers-client/pom.xml), [book-service](https://github.com/Antonyo891/SpringBoot/blob/seminar_eleven/books-client/pom.xml), [issue-service](https://github.com/Antonyo891/SpringBoot/blob/seminar_eleven/issues-client/pom.xml), ...

Шаги реализации:
1. Создаем новый модуль в микросервисном проекте - это и будет наш стартер
2. Берем код с ДЗ-8 (класс аспекта и аннотации) и переносим в стартер
3. В стартере декларируем Configuration и внутри нее декларируем бин - аспект
4. В проекте стартера в resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports прописываем полный путь конфигурации
5. Подключаем зависимость стартера (pom-dependency) в микросервисы
6. Проверяем, что аспект работает

Доп. задание (со звездочкой): придумать точки расширения\конфигурирования аспекта:
Включить\выключить по флажку в конфиге (ConditionalOnProperty)

   </details>



