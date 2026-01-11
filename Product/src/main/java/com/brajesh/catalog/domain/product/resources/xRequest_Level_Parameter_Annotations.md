# REQUEST PARAMETER 

# Spring Boot Controller Method Parameter Resolution

In **Spring Boot**, controller method parameters are resolved at runtime by the **Spring MVC infrastructure** using `HandlerMethodArgumentResolver`.

This mechanism:

- Binds each parameter from a **single HTTP request source**, such as:
    - Path variables
    - Query parameters
    - Headers
    - Cookies
    - Request body

- Enforces **mandatory and optional behavior** through:
    - Explicit annotations (e.g., `@PathVariable`, `@RequestParam`, `@RequestHeader`, `@RequestBody`)
    - Validation mechanisms (e.g., `@Valid`, `@NotNull`)

# REQUEST PARAMETER ANNOTATION
📌 Spring Boot Controller Parameter Binding Table

| Annotation        | What it reads | Comes from (HTTP Request) | Mandatory by Default (Spring Boot) | How to make Optional                            | Typical Spring Boot Use Case     |
|-------------------|---------------|---------------------------|------------------------------------|-------------------------------------------------|----------------------------------|
| `@RequestParam`   | Single value  | Query string / form field | ❌ No                               | `required=false`, `Optional<T>`, `defaultValue` | Filters, pagination, search APIs |
| `@PathVariable`   | Single value  | URI path                  | ✅ Yes                              | ❌ Cannot be optional in practice                | REST resource identification     |
| `@RequestBody`    | Full object   | HTTP body (JSON/XML)      | ✅ Yes                              | `required=false`                                | Create / update REST APIs        |
| `@RequestHeader`  | Single value  | HTTP headers              | ❌ No                               | `required=false`, `defaultValue`                | Auth tokens, tracing headers     |
| `@CookieValue`    | Single value  | Cookies                   | ❌ No                               | `required=false`                                | Session-based applications       |
| `@ModelAttribute` | Object        | Query + form parameters   | ❌ No                               | Field-level validation                          | Form binding, search objects     |



# Spring Boot Request Parameter Annotations - Interview Cheat Sheet

This cheat sheet contains concise examples for all key Spring Boot request parameter annotations, ready for memorization.

---

## 1️⃣ @RequestParam – Query / Form Parameter

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    // Example: GET /products/search?name=phone&page=2
    @GetMapping("/search")
    public String search(
            @RequestParam String name,                    // mandatory
            @RequestParam(required = false, defaultValue = "1") int page // optional
    ) {
        return "Searching for: " + name + ", page: " + page;
    }
}
```

> Typical Use: Filters, pagination, search APIs

---

## 2️⃣ @PathVariable – URI Path Parameter

```java
@RestController
@RequestMapping("/users")
public class UserController {

    // Example: GET /users/10
    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "User ID: " + id;
    }
}
```

> Typical Use: REST resource identification (mandatory)

---

## 3️⃣ @RequestBody – HTTP Body Parameter

```java
@RestController
@RequestMapping("/orders")
public class OrderController {

    // Example: POST /orders with JSON body { "productId": 101, "quantity": 2 }
    @PostMapping
    public String createOrder(@RequestBody OrderRequest request) {
        return "Order created for product: " + request.getProductId() + ", quantity: " + request.getQuantity();
    }
}

class OrderRequest {
    private int productId;
    private int quantity;

    // getters & setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
```

> Typical Use: Create / update REST APIs (mandatory by default)

---

## 4️⃣ @RequestHeader – HTTP Header Parameter

```java
@RestController
@RequestMapping("/api")
public class HeaderController {

    // Example: GET /api/data with header Authorization: Bearer abc123
    @GetMapping("/data")
    public String getData(@RequestHeader("Authorization") String authHeader) {
        return "Received header: " + authHeader;
    }
}
```

> Typical Use: Auth tokens, tracing headers (optional with `required=false`)

---

## 5️⃣ @CookieValue – Cookie Parameter

```java
@RestController
@RequestMapping("/session")
public class SessionController {

    // Example: GET /session/info with cookie SESSIONID=abc123
    @GetMapping("/info")
    public String getSession(@CookieValue("SESSIONID") String sessionId) {
        return "Session ID: " + sessionId;
    }
}
```

> Typical Use: Session-based applications (optional with `required=false`)

---

## 6️⃣ @ModelAttribute – Bind Query/Form Params to Object

```java
@RestController
@RequestMapping("/search")
public class SearchController {

    // Example: GET /search/products?keyword=phone&page=2
    @GetMapping("/products")
    public String search(@ModelAttribute SearchRequest request) {
        return "Searching for: " + request.getKeyword() + ", page: " + request.getPage();
    }
}

class SearchRequest {
    private String keyword;
    private int page = 1; // default value

    // getters & setters
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
}
```

> Typical Use: Form binding, search objects

---

### ✅ Notes for Interview

* Spring Boot automatically configures Spring MVC, which resolves request parameters using `HandlerMethodArgumentResolver`.
* Each parameter is bound from exactly **one request source** (path, query, header, cookie, or body).
* Mandatory or optional behavior is controlled via annotations (`required=false`, `defaultValue`) or validation (`@NotNull`, `@Valid`).
