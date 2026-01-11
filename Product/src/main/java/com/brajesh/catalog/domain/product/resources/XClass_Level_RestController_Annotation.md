# @RestController
## Interview-Ready One-Line Answer
# @RestController in Spring Boot

If a class is annotated with `@RestController`, Spring Boot treats it as a **specialized version of the `@Controller` annotation**.

It effectively **combines** the `@Controller` and `@ResponseBody` annotations into a single annotation.

```text
@RestController = @Controller + @ResponseBody
```
> `@RestController` is used to build **RESTful services**, where responses are written 
> **directly to the HTTP response body**.


At the **class level**, `@RestController` is commonly combined with:

- `@RequestMapping` – for routing
- `@Validated` – for input validation
- Security annotations – for access control
- CORS configuration – to allow cross-origin requests
- OpenAPI / Swagger annotations – for API documentation

For example, in **microservices**, we often apply **security and CORS at the controller level** to enforce consistent API behavior.

Architect-Level One-Line Answer (Improved)

If you want a pure architect answer, use this:

### Architect-Level One-Line Answer
“@RestController acts as the HTTP inbound adapter, enforcing API boundaries, 
handling protocol translation, validation, and serialization, 
while delegating all business logic to the application layer.”


## 1⃣ Serialization Flow (Important)
```mermaid
flowchart TD
    A[Client Request]
    B[@RestController]
    C[Return Object]
    D[HttpMessageConverter]
    E[JSON / XML]
    F[HTTP Response Body]

    A --> B
    B --> C
    C --> D
    D --> E
    E --> F

```
# Controller-Level Annotations (Class-Level Only)

> Scope: **Only annotations applied at `@RestController` class level**  
> Principle: **Controller = Request handling only**

---

## ✅ Allowed Class-Level Annotations on `@RestController` (Best Practice)

| Annotation | Purpose | Why It Belongs in Controller |
|----------|--------|-----------------------------|
| `@RestController` | Marks class as REST controller | Core request handler |
| `@RequestMapping` | Base URI mapping | Request routing |
| `@CrossOrigin` | Enable CORS | Request-level concern |
| `@Validated` | Enable validation | Request input validation |
| `@InitBinder` | Customize request data binding | Request preprocessing |
| `@Tag` | API grouping (OpenAPI/Swagger) | Documentation only |
| `@ApiResponses` | API response documentation | Documentation only |
| `@SecurityRequirement` | Declare security scheme | API contract metadata |

---

## ❌ Not Recommended at Controller Class Level (Breaks SRP)

| Annotation | Reason | Correct Layer |
|----------|--------|---------------|
| `@Transactional` | Business / transaction concern | Service |
| `@PreAuthorize` | Authorization rule | Method / Security |
| `@RolesAllowed` | Authorization rule | Method / Security |
| `@Profile` | Environment configuration | Configuration |
| `@ConditionalOnProperty` | Feature toggle | Configuration |
| `@Service` | Business logic | Service |
| `@Repository` | Persistence logic | Repository |
| `@Component` | Generic stereotype | Redundant |
| `@Configuration` | Bean configuration | Configuration |
| `@Entity` | Database mapping | Domain |

---

## 🎯 Ideal Controller Template (Senior-Approved)

```java
@RestController
@RequestMapping("/products")
@Validated
@Tag(name = "Products API")
@SecurityRequirement(name = "bearerAuth")
public class ProductResource {
}
```
# METHOD LEVEL ANNOTATION
Spring provides dedicated HTTP method mapping annotations like @GetMapping, @PostMapping, @PutMapping, @PatchMapping, 
and @DeleteMapping, which are specialized forms of @RequestMapping.

## List of Core HTTP Method Annotations


## 📌 Core HTTP Method Annotations with CRUD Classification

| Annotation       | HTTP Method | CRUD Action | Typical REST Use        | Update Type        | Idempotent |
|------------------|-------------|-------------|-------------------------|--------------------|------------|
| @GetMapping      | GET         | **Read**    | Read resource(s)        | N/A                | ✅ Yes      |
| `@PostMapping`   | POST        | **Create**  | Create resource         | N/A                | ❌ No       |
| `@PutMapping`    | PUT         | **Update**  | Replace entire resource | **Full Update**    | ✅ Yes      |
| `@PatchMapping`  | PATCH       | **Update**  | Partial update          | **Partial Update** | ⚠️ Depends |
| `@DeleteMapping` | DELETE      | **Delete**  | Delete resource         | N/A                | ✅ Yes      |


## 🔄 Full Update vs Partial Update

### ✅ `PUT` — Full Update
- Replaces the **entire resource**
- Requires **complete object**
- Missing fields may be removed
- Always idempotent

### ⚠️ `PATCH` — Partial Update
- Updates **only specified fields**
- Smaller payload
- Idempotency depends on implementation

---

## 🔁 Idempotency Summary

| HTTP Method | Idempotent  |
|-------------|-------------|
| GET         | ✅ Yes       |
| POST        | ❌ No        |
| PUT         | ✅ Yes       |
| PATCH       | ⚠️ Depends  |
| DELETE      | ✅ Yes       |

---

## 🔁 Idempotency Explained (Important)

**Idempotency** means:
> Multiple identical requests produce the **same result**.



## 🎯 REST Best Practices

- Use **POST** only for creation
- Use **PUT** for full replacement
- Use **PATCH** for partial modification
- Never mix CRUD semantics
- Keep controllers thin

---

## ❌ Common REST Anti-Patterns

- Using POST for updates ❌
- Using PUT for partial updates ❌
- Exposing DB entities directly ❌
- Ignoring idempotency ❌

### Examples:
- `GET /products/1` → Always returns the same resource
- `PUT /products/1` → Replaces the resource consistently
- `DELETE /products/1` → Deleting multiple times has the same effect

⚠️ `POST` is **not idempotent** because each request may create a new resource.

---

## 🌐 Real API Examples

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public ProductResponseDto createProduct(
            @RequestBody @Valid ProductRequestDto request) {
        return null;
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(
            @PathVariable String id,
            @RequestBody @Valid ProductRequestDto request) {
        return null;
    }

    @PatchMapping("/{id}")
    public ProductResponseDto partiallyUpdateProduct(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
    }
}
