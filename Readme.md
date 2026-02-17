# 🚀 TaskFlow API

TaskFlow is a RESTful Task Management Backend built using Spring Boot and PostgreSQL.  
The project demonstrates clean architecture principles, DTO mapping, validation, exception handling, and database relationships.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- MapStruct (if used)
- Docker (coming soon)

---

## 📌 Features

- Create, update, delete Task Lists
- Create, update, delete Tasks
- Entity relationships (Task ↔ TaskList)
- DTO Layer
- Mapper Layer
- Global Exception Handling
- Clean layered architecture

---

## 📂 Project Structure

```
controller
service
repository
dto
mapper
model
enums
exception
```

---

## 🗄 Database

PostgreSQL is used as the primary database.

Update your `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/taskflow
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## ▶️ How to Run

1. Run PostgreSQL
2. Clone the project
3. Run:

```
mvn clean install
mvn spring-boot:run
```

---

## 📮 Sample API Endpoints

Task Lists:
```
GET     /api/v1/task-lists
POST    /api/v1/task-lists
GET     /api/v1/task-lists/{taskListId}
PUT     /api/v1/task-lists/{taskListId}
DELETE  /api/v1/task-lists/{taskListId}
```

Tasks:
```
GET     /api/v1/task-lists/{taskListId}/tasks
POST    /api/v1/task-lists/{taskListId}/tasks
GET     /api/v1/task-lists/{taskListId}/tasks/{taskId}
PUT     /api/v1/task-lists/{taskListId}/tasks/{taskId}
DELETE  /api/v1/task-lists/{taskListId}/tasks/{taskId}
```


---

## 🔜 Upcoming Improvements

- Pagination & Sorting
- JWT Authentication
- Docker Support

---

## 👨‍💻 Author

Abdelrahman Osama  
Backend Developer – Spring Boot