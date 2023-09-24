# springboot-bookstore-crud-practice

[springboot-bookstore-crud-practice](https://github.dev/mamun-swe/springboot-bookstore-crud-practice) is a basic CRUD REST API application. Also, it's very helpful for beginners, who want to build his/her career in Java Spring Boot.

## Configuration

Clone the application from github using given git command.

```ssh
    git clone https://github.com/mamun-swe/springboot-bookstore-crud-practice
```

Then open application directory and open it to your favourite code editor, then go to src/main/resources and open application.properties file to your code editor and add your database URL, username and password. An example given below:

```
spring.datasource.url=jdbc:mysql://172.20.0.1:3306/<DATABASENAME>
spring.datasource.username=<DATABASE USERNAME>
spring.datasource.password=<DATABASE PASSWORD>
```

Finally all environment setup successfully completed, Now run the application and browse given API endpoints to your postman.

## Modules

There are 2 main modules need in application prototypes:

1. Category
2. Book

## Available API endpoints:

    - Category
        - [GET] http://localhost:8081/api/v1/categories
        - [POST] http://localhost:8081/api/v1/categories
        - [GET] http://localhost:8081/api/v1/categories/:id
        - [PUT] http://localhost:8081/api/v1/categories/:id
        - [DELETE] http://localhost:8081/api/v1/categories/:id

    - Book
        - [GET] http://localhost:8081/api/v1/books
        - [POST] http://localhost:8081/api/v1/books
        - [GET] http://localhost:8081/api/v1/books/:id
        - [PUT] http://localhost:8081/api/v1/books/:id
        - [DELETE] http://localhost:8081/api/v1/books/:id
