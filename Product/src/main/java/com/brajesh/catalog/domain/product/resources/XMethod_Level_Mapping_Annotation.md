# METHOD LEVEL ANNOTATION
Spring provides dedicated HTTP method mapping annotations like @GetMapping, @PostMapping, @PutMapping, @PatchMapping,
and @DeleteMapping, which are specialized forms of @RequestMapping.


# @RequestMapping

        @RequestMapping(
        value = "/products",
        method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json",
        headers = "X-API-VERSION=1",
        params = "active=true"
        )


@PostMapping == @RequestMapping(method = RequestMethod.POST)

Yes ✅ — all attributes in 
@RequestMapping are optional, 
same for all Mapping 



## List of Core HTTP Method Annotations
## 📌 Core HTTP Method Annotations with CRUD Classification

| Annotation       | HTTP Method | CRUD Action | Typical REST Use        | Update Type        | Idempotent |
|------------------|-------------|-------------|-------------------------|--------------------|------------|
| `@GetMapping`    | GET         | **Read**    | Read resource(s)        | N/A                | ✅ Yes      |
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


