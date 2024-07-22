<details>
  <summary>9 семинар дз:</summary> <br> 
  1.Восстановить пример, рассмотренный на уроке (запустить эврику и 2 сервиса; заставить их взаимодействовать)
Сдать скриншот страницы /eureka/apps с зарегистрированными приложениями.
На скрине должно быть видно оба сервиса (book-service, issuer-service) <br>
  
![screen](https://github.com/Antonyo891/SpringBoot/blob/seminar_eleven/src/main/resources/Eureka.png)
  
</details> <br> 
<details>
  <summary> 11 семинар дз:</summary> Проблематика: имеется несколько микросервисов (проектов) на spring-boot: reader-service, book-service, issue-service, ...
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
</summary>details>


