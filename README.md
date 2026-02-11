# Shopping Backend

A backend REST API for a shopping (e-commerce) application built using **Spring Boot** and **Spring Data JPA**.  
This project focuses on **backend API design, database relationships, and business logic**.

---

## 🚀 Features Implemented

### Product Module
- Add product
- View all products / product by ID
- Update product
- Delete product

### Order Module
- Place order
- Order with multiple order items
- Stock quantity update during order placement

### Backend Architecture
- One-to-Many & Many-to-One JPA relationships
- DTO-based request and response handling
- Layered architecture (Controller → Service → Repository)

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

---

## 📁 Project Structure

```text
src/main/java/org/prathmesh/shopping_backend
│
├── Controller
│   ├── HealthCheck.java
│   ├── OrderController.java
│   └── ProductController.java
│
├── Model
│   ├── DTOs
│   │   ├── OrderRequest.java
│   │   ├── OrderResponse.java
│   │   ├── OrderItemRequest.java
│   │   └── OrderItemResponse.java
│   │
│   ├── Order.java
│   ├── OrderItem.java
│   └── Product.java
│
├── Repository
│   ├── OrderRepo.java
│   └── ProductRepo.java
│
├── Service
│   ├── OrderService.java
│   └── ProductService.java
│
└── ShoppingBackendApplication.java


🔄 API Testing
Backend APIs were tested using Postman
A third-party frontend UI was used locally only for testing API integration
Frontend code is not included in this repository and was not developed by me

🔐 Configuration
Database credentials are not committed for security reasons.
Create application.properties in:
src/main/resources/

spring.datasource.url=jdbc:postgresql://localhost:5432/shopping_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


🚧 Upcoming Enhancements
Transaction management using @Transactional
User authentication & authorization (Spring Security + JWT)
Role-based access control (ADMIN / USER)
Global exception handling
Order history per user

🎯 Project Status
🚧 Under active development
This project is being continuously improved while learning and implementing backend best practices.
