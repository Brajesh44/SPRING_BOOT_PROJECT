# SPRING_BOOT_PROJECT

## Project Overview

This project focuses on **Spring Boot core concepts**, following **clean code and clean architecture principles**.

> This structure follows **package-by-feature**, driven by:
- Single Responsibility Principle (SRP)
- Separation of Concerns
- Clean Architecture

It improves **cohesion**, reduces **coupling**, and **scales well for microservices**.

---

## Mandatory Dependencies

Mandatory dependencies are required to create and run a Spring Boot project successfully.  
These typically include:
- spring-boot-starter-webflux  (Spring Boot Starter Web) 
- Spring Boot Starter Validation
- spring-boot-starter-data-mongodb-reactive (Spring Boot Starter Data JPA (if persistence is used))

---

## 2️⃣ Role of Each Object

### ✅ ProductRequestDto

➡️ **Represents client input**

- Contains only fields the client is allowed to send
- Focused on validation
- Must not contain database or internal fields

```java
public class ProductRequestDto {

    @NotBlank
    private String name;

    @Positive
    private BigDecimal price;
}
```

### ✅ Product (Domain / Entity)

➡️ **Represents business data**

- Internal domain model
- May contain business logic
- May contain database-related fields

```java
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private Instant createdAt;
}
```

### ✅ ProductResponseDto

➡️ **Represents API output**

- Contains only what the client is allowed to see
- Can hide sensitive or internal fields
- Can enrich or transform data for response

```java
public class ProductResponseDto {

    private String id;
    private String name;
    private BigDecimal price;
}
```
## 3️⃣ Why They Should **NOT** Be the Same (Important)

### ❌ Problems if the Same Object Is Reused

| Issue               | Explanation                                    |
|---------------------|------------------------------------------------|
| Security            | Client may send `id`, `createdAt`              |
| Tight coupling      | API becomes tightly coupled to the database    |
| Breaking changes    | Database changes break API contracts           |
| Validation conflict | Request and response validation rules differ   |
| OCP violation       | Hard to evolve without modifying existing code |

