# **Тестовое задание для Digital Chief**

## Описание

### [Задание](./documentation/task.pdf)

## Используемые технологии

* **Maven**
* **Spring Boot 3.3.1**
* **Java 17**
* **PostgreSQL database**
* **Hibernate**
* **logs**
* **Lombok**
* **MapStruct**
* **Swagger**
* **Postman**

## Как запустить проект

* `git clone https://github.com/Kenor007/DigitalChief-TestTask.git`
* Открыть проект с помощью IDE
* установить PostgreSQL, создать базу данных `client_order_db` (`username: postgres` , `password: postgres`)
* создать таблицы, используя [ddl.sql](./src/main/resources/ddl.sql)

## Сборка и запуск проекта

* `mvn package`
* `java -jar target/client_order-1.0.jar`

Альтернативный способ:

* `mvn spring-boot:run`
* Открыть в браузере Swagger `http://localhost:8092/swagger-ui/index.html` для ознакомления со всеми эндпоинтами

## Особенности

Для приложения была выбрана бизнес-модель из двух сущностей `Клиент - Заказы` с отношением "один ко многим" (один Клиент
может иметь много Заказов).

Сущность Клиент состоит из следующих свойств:

* Имя клиента
* Телефонный номер клиента
* Адрес клиента
* email клиента
* Список заказов клиента

Сущность Заказ состоит из следующих свойств:

* Описание заказа
* Сумма заказа (с точностью до 2 знаков после запятой)
* Дата и время заказа
* Состояние заказа COMPLETED / PENDING (завершённый или ожидаемый)

Приложение запускается на порту `8092`.
Реализована валидация входных данных. В случае не валидных данных в ответе указаны сообщение с описанием исключительной
ситуации, HttpStatus, описание и
timestamp.
Выполнена реализация CRUD операций для сущностей.
Подготовлена коллекция запросов для полной проверки REST API с помощью Postman.

### [Коллекция Postman](./documentation/ClientOrderTestTaskPostmanCollection.json)