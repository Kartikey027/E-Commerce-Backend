# 🛒 E-commerce Backend API - Spring Boot

This is a fully functional **E-commerce backend API** built using Spring Boot. It supports product management, category filtering, user registration and authentication (JWT-based), cart management, and order placement.

This project follows a clean, layered architecture and is designed to serve as the backend for an online shopping platform.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Authorization  
- 👤 User Signup & Login  
- 📦 Product Management (CRUD)  
- 🏷️ Category Management  
- 🛒 Cart Functionality (Add, Remove, View Items)  
- 📥 Order Placement & Tracking  
- 📃 Pagination & Filtering Support (optional)  
- 🧪 Exception Handling & Validation  

---

## 🛠️ Tech Stack

- Java 17+  
- Spring Boot 3  
- Spring Security  
- Spring Data JPA  
- Hibernate  
- JWT (JSON Web Token)  
- MySQL / H2 Database  
- Lombok  
- ModelMapper  
- Maven  

---

## 🧱 Project Structure

```
.
├── config
│   └── SecurityConfig.java, JWT filters etc.
├── controller
│   └── ProductController.java, AuthController.java, ...
├── service
│   └── ProductService.java, UserService.java, ...
├── repository
│   └── ProductRepository.java, UserRepository.java, ...
├── model
│   └── Product.java, User.java, Cart.java, Order.java
├── dto
│   └── ProductDto.java, AuthRequest.java, AuthResponse.java, ...
├── exception
│   └── GlobalExceptionHandler.java
└── EcommerceApplication.java
```

---

## 🧪 Getting Started

### ✅ Prerequisites

- Java 17+  
- Maven  
- MySQL or H2 Database  

---

### 🔧 Setup Instructions

1. **Clone the repo**

```bash
git clone https://github.com/your-username/ecommerce-backend.git
cd ecommerce-backend
```

2. **Configure Database**  
Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. **Build & Run**

```bash
mvn clean install
mvn spring-boot:run
```

4. **API Base URL**

```
http://localhost:8080/api/
```
