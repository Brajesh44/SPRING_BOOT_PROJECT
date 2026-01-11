# Entity Related Annotation

```java

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

```
## @Document

### These annotations are used at the **top level of an Entity**.

_org.springframework.data.mongodb.core.mapping.Document_

##### @Document(collection = "products")

#### Purpose

Marks a class as a MongoDB document (collection).
#### What it does

1. Maps the class to a MongoDB collection
2. Default collection name = class name (lowercase)
3. Enables Spring Data MongoDB repositories

#### When to use
✔ MongoDB

❌ Relational databases
#### #Architect note 🧠

1. @Document is infrastructure-level, not domain logic or not Application Layer 
2. Avoid mixing heavy business logic in document classes.
3. Keep entities lean and focused on persistence mapping.

## @Data
📦 Package
```java
import lombok.Data;
```
#### Purpose
@Data removes boilerplate code by generating getters, setters, constructors, toString, equals, and hashCode at compile time.

##### It generates
1. @Getter 
2. @Setter
3. toString()
4. equals()
5. hashCode()
6. RequiredArgsConstructor 

##### Architect caution ⚠️

❌ Avoid on JPA entities
❌ Avoid when object should be immutable

## @AllArgsConstructor

#### Purpose
Generates constructor with all fields

## @NoArgsConstructor
Generates no-argument constructor
```java
@NoArgsConstructor
public class Product {
}

```

#### Why important

###### ✔ Required by:
1. JPA
2. Jackson
3. Hibernate
4. MongoDB

##### Architect rule 🧠

###### Persistence frameworks require no-arg constructors.

## @Builder
#### 🔍 Purpose

###### Implements Builder Design Pattern

```java
@Builder
public class Product {
    private String id;
    private String name;
}

```
```java
Product p = Product.builder()
                   .id("P1")
                   .name("Laptop")
                   .build();

```
#### Benefits

✔ Readable
✔ Immutable-friendly
✔ Avoids constructor explosion

Image 10-01-26 at 12.53.jpg