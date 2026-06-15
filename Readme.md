# Product Service

## Service Overview
Manages the product catalog for ShopSphere e-commerce platform. Provides CRUD operations, keyword search, pagination, and sorting capabilities for products across multiple categories.

## Tech Stack

| Component | Version |
|-----------|---------|
| Java | 21 |
| Spring Boot | 4.0.5 |
| Spring Cloud | 2025.1.1 |
| Spring Data JPA | Latest |
| MySQL | Latest |
| Eureka Client | Netflix Spring Cloud |
| Lombok | Latest |
| MapStruct | 1.6.3 |

## API Endpoints

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| POST | `/v1/api/products` | Create a new product | 201 |
| GET | `/v1/api/products` | Get all products with pagination & sorting | 200 |
| GET | `/v1/api/products/{id}` | Get product by ID | 200 |
| PUT | `/v1/api/products/{id}` | Update product | 200 |
| DELETE | `/v1/api/products/{id}` | Delete product | 204 |
| GET | `/v1/api/products/search?keyword=...` | Search products by name (paginated) | 200 |

**Query Parameters:**
- `page` (default: 0) - Page number for pagination
- `size` (default: 10) - Page size
- `sortBy` (default: id) - Sort field
- `sortDirection` (default: asc) - Sort order (asc/desc)
- `keyword` - Search term (for search endpoint, default size: 5)

## Key Features

- **Keyword Search** — Case-insensitive product search with pagination
- **Pagination & Sorting** — Configurable page size and sort direction
- **Category Support** — Products can be organized by category
- **Validation** — Request validation (NotBlank name, Positive price)
- **Custom Exception Handling** — ProductNotFoundException for missing products

## How to Run

**Prerequisites:**
- Java 21+
- MySQL 8.0+
- Eureka Discovery Server running at `http://localhost:8761`

**Run Commands:**

Windows:
```bash
./gradlew.bat bootRun
```

Linux/Mac:
```bash
./gradlew bootRun
```

**Service runs on port 8081** 

# **Implementation of Swagger UI:**
- Swagger UI: `http://localhost:8081/swagger-ui/index.html`
- OpenAPI spec (JSON): `http://localhost:8081/v3/api-docs`
- All APIs are public; authentication is not required.
