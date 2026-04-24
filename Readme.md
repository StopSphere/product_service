#  Product Service – ShopSphere

##  Overview

Product Service is a core microservice in the ShopSphere e-commerce system.
It is responsible for managing product data and exposing clean, scalable APIs for product operations.

This service is designed with production-level backend practices including layered architecture, DTO-based design, pagination, and structured exception handling.

---

##  Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* MapStruct
* Lombok
* MySQL (Dockerized)
* Gradle

---

##  Features Implemented

###  Core Features

* Create Product
* Get Product by ID
* Get All Products
* Update Product
* Delete Product

---

###  Pagination (Production-Level)

* Efficient data fetching using `Pageable`
* Custom response structure (no raw Spring Page exposure)

```json
{
  "content": [...],
  "page": 0,
  "size": 10,
  "totalElements": 22,
  "totalPages": 3,
  "isLast": false
}
```

---

### Exception Handling

* Custom exception (`ProductNotFoundException`)
* Global exception handler using `@RestControllerAdvice`
* Structured error response

---

###  Validation

* Input validation using `@Valid`
* Field-level constraints (`@NotBlank`, `@Positive`, etc.)
* Clean error messages for invalid requests

---

###  DTO-Based Architecture

* No direct exposure of entity
* Separate request & response DTOs
* Mapping handled via MapStruct

---

##  Architecture

```text
Controller → Service → Repository → Database
```

### Layer Responsibilities:

* **Controller** → Handles HTTP requests
* **Service** → Business logic
* **Repository** → Database interaction
* **DTOs** → API contract
* **Mapper** → Entity ↔ DTO conversion

---

##  Configuration

Located in:

```text
src/main/resources/application.yml
```

### Example DB Config:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/product_db
    username: root
    password: 1234
```

---

##  Run Locally

### 1. Start MySQL (Docker)

```bash
docker compose up -d
```

---

### 2. Run Application

```bash
./gradlew bootRun
```

---

##  API Endpoints

Base URL:

```text
/v1/api/products
```

### 🔹 Create Product

`POST /v1/api/products`

---

### 🔹 Get All Products (with pagination)

`GET /v1/api/products?page=0&size=5`

---

### 🔹 Get Product by ID

`GET /v1/api/products/{id}`

---

### 🔹 Update Product

`PUT /v1/api/products/{id}`

---

### 🔹 Delete Product

`DELETE /v1/api/products/{id}`

---

##  Design Decisions

* UUID used for product IDs (microservice-friendly)
* DTO pattern for clean API contracts
* Custom pagination response for frontend usability
* Global exception handling for consistency
* Validation at API layer for data integrity

---

##  Current Status

✔ APIs implemented
✔ Pagination with clean response
✔ Validation & exception handling
✔ Production-ready layered structure

---

 Upcoming Enhancements

* Sorting support
* Search API
* Inventory/stock management
* Service-to-service communication

---

##
