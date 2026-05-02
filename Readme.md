# Product Service

REST API for product management. Provides CRUD, pagination, sorting, and search with service discovery via Eureka and API Gateway integration.

## Tech Stack

- Java 21 · Spring Boot · Spring Data JPA
- MySQL · MapStruct · Lombok
- Eureka Client · Spring Cloud

## Quick Start

**Prerequisites:** Discovery Server running on port 8761, MySQL on 3307

```powershell
# Start MySQL
docker compose up -d

# Run service
.\gradlew.bat bootRun
```

Service will register with Eureka as `product-service` on port 8081.

## Configuration

Edit `src/main/resources/application.yml`:

```yaml
server:
  port: 8081

spring:
  application:
    name: product-service
  datasource:
    url: jdbc:mysql://localhost:3307/product_db
    username: root
    password: 1234
  jpa:
    hibernate:
      ddl-auto: update

eureka:
  client:
    service-url:
      defaultZone: http://host.docker.internal:8761/eureka/
    register-with-eureka: true
  instance:
    prefer-ip-address: true
```

## API Endpoints

Base: `/v1/api/products`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create product |
| GET | `/` | List products (paginated, sortable) |
| GET | `/{id}` | Get product by ID |
| PUT | `/{id}` | Update product |
| DELETE | `/{id}` | Delete product |
| GET | `/search` | Search by name |

### Query Parameters

**List/Search:**
- `page`: 0-based (default: 0)
- `size`: page size (default: 10)
- `sortBy`: field name (default: id)
- `sortDirection`: `asc` or `desc` (default: asc)

Valid sort fields: `id`, `name`, `price`, `category`, `createdAt`, `updatedAt`, `available`

### Examples

**Create Product**
```bash
curl -X POST http://localhost:8081/v1/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Wireless Mouse",
    "description": "Ergonomic wireless mouse",
    "price": 19.99,
    "category": "accessories",
    "imageUrl": "https://example.com/mouse.png"
  }'
```

**List with Pagination & Sorting**
```bash
curl "http://localhost:8081/v1/api/products?page=0&size=10&sortBy=price&sortDirection=desc"
```

**Search**
```bash
curl "http://localhost:8081/v1/api/products/search?keyword=mouse&page=0&size=5"
```

### Response

**Single Product:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Wireless Mouse",
  "description": "Ergonomic wireless mouse",
  "price": 19.99,
  "category": "accessories",
  "imageUrl": "https://example.com/mouse.png",
  "available": true
}
```

**Paginated Response:**
```json
{
  "products": [],
  "page": 0,
  "size": 10,
  "total": 22,
  "totalPages": 3,
  "isLastPage": false
}
```

## DTOs

| DTO | Fields |
|-----|--------|
| CreateProductRequestDTO | name* · description · price* · category · imageUrl |
| UpdateProductRequestDTO | name · description · price · category · imageUrl |
| ProductResponseDTO | id · name · description · price · category · imageUrl · available |
| PagedResponse<T> | products · page · size · total · totalPages · isLastPage |

*required fields

## Project Structure

```
src/main/java/com/shopsphere/product_service/product_service/
├── Controller/         (REST endpoints)
├── Service/            (business logic)
├── Repository/         (data access)
├── Entity/             (JPA entities)
├── DTO/                (request/response DTOs)
├── Mapper/             (MapStruct mappers)
├── Exception/          (global error handling)
└── ProductServiceApplication.java
```

## Tests

```powershell
.\gradlew.bat test
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| DB connection fails | Verify MySQL running, check credentials in `application.yml` |
| Eureka registration fails | Check discovery server at `http://localhost:8761`, verify `defaultZone` URL |
| Invalid sort field error | Use only valid fields: `id`, `name`, `price`, `category`, `createdAt`, `updatedAt`, `available` |
| DTO mapping issues | Check MapStruct generated classes in `build/` folder |
| API Gateway routing fails | Verify gateway routes to service name `product-service` |

## Status

✅ Rest APIs (Basic operations) 
✅ Pagination & sorting  
✅ Search functionality  
✅ Validation & error handling  
✅ Eureka service discovery  
✅ API Gateway ready  
✅ Spring Cloud resilience  

## Architecture Notes

- **Service Discovery:** All service instances auto-register with Eureka
- **API Gateway:** Routes requests using service name `product-service`
- **Resilience:** Spring Cloud handles timeouts and failures
- **Database:** JPA with Hibernate, auto schema updates on startup
- **Mapping:** MapStruct provides zero-overhead entity - DTO conversion
s