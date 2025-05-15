# 🍽️ Restaurant API (v0.1.9)

A simple REST API to manage restaurants, orders, customers, menu items, and beverages.

Base URL: https://restaurantapi.com

## ⚙️ About the Project

This API is built with:

- **Java & Spring Boot**
- **RESTful architecture**
- **Maven** for project management
- **MariaDB / MySQL** as the database
- **JUnit & Mockito** for unit testing
- **Lombok** for cleaner code
- **Spring Data JPA** for data access
- **Validation** using `javax.validation`
- **Exception handling** with custom error responses
- **DTOs** to separate data models from entities
- **Docker support** for containerized deployment

---

## 📚 Main Endpoints

### 🏪 Restaurants
- GET /restaurants – List all
- POST /restaurants – Create new
- GET /restaurants/{id} – Get by ID
- PUT /restaurants/{id} – Replace by ID
- PATCH /restaurants/{id} – Partially update by ID
- DELETE /restaurants/{id} – Delete by ID

### 📦 Orders
- GET /orders
- POST /orders
- GET /orders/{id}
- PUT /orders/{id}
- PATCH /orders/{id}
- DELETE /orders/{id}

### 👥 Customers
- GET /customers
- POST /customers
- GET /customers/{id}
- PUT /customers/{id}
- PATCH /customers/{id}
- DELETE /customers/{id}

### 🍽️ Menu Items
- GET /menuitems
- POST /menuitems
- GET /menuitems/{id}
- PUT /menuitems/{id}
- PATCH /menuitems/{id}
- DELETE /menuitems/{id}

### 🍹 Beverages
- GET /beverages
- POST /beverages
- GET /beverages/{id}
- PUT /beverages/{id}
- PATCH /beverages/{id}
- DELETE /beverages/{id}

---

## ❗ Error Codes

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 404 Not Found
- 500 Internal Server Error
