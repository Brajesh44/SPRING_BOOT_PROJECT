# Test Container and Annotation

1. Spring Boot 3.1 integrates Testcontainers deeply. The @TestConfiguration class defines containers as Spring beans.
2. @ServiceConnection automatically wires container connection properties into Spring Boot auto-configuration.
3. @SpringBootTest boots the full context for integration testing, and the JUnit Platform Launcher orchestrates execution.
4. The separate main class allows running the app with containers outside JUnit.

```text
High-Level JUnit 5 Architecture (Say this first)
```

#### JUnit 5 is composed of three main modules:

1. JUnit Platform – test engine & launcher
2. JUnit Jupiter – new programming & annotation model
3. JUnit Vintage – support for JUnit 3 & 4

#### Dependency vs Package (Common Trap ❌)

 Dependency name ≠ Package name 

```text
| Dependency                | Main Package                  |
| ------------------------- | ----------------------------- |
| `junit-jupiter-api`       | `org.junit.jupiter.api`       |
| `junit-jupiter-params`    | `org.junit.jupiter.params`    |
| `junit-platform-launcher` | `org.junit.platform.launcher` |

````````

 Most Important Packages

🔹 1. **org.junit.jupiter.api**
# JUnit Jupiter API (`org.junit.jupiter.api`)

> **MOST IMPORTANT – ~90% usage in real projects and interviews**

This is the **primary JUnit 5 package** 
that interviewers usually expect you to know.

---

## What This Package Contains

- Test annotations
- Assertions
- Assumptions
- Lifecycle callbacks

---

## Key Annotations

```java
@Test
@BeforeEach
@AfterEach
@BeforeAll
@AfterAll
@Disabled
@Nested
@DisplayName
@RepeatedTest
@Tag
```

#### 2. org.junit.jupiter.params

```java
org.junit.jupiter.params
org.junit.jupiter.params.provider

@ParameterizedTest
@ValueSource
@CsvSource
@MethodSource
@EnumSource

```


### Spring Boot + JUnit + Testcontainers Execution Flow

```text
JVM starts
 ├── JUnit Platform Launcher
 │    ├── SpringExtension
 │    │    ├── Starts Spring ApplicationContext
 │    │    ├── Loads TestcontainersConfiguration
 │    │    │    ├── Starts MongoDB Docker container
 │    │    │    └── Registers connection properties
 │    │    └── Auto-configures MongoTemplate
 │    └── Jupiter Engine executes @Test
 └── JVM shuts down → containers stop

```
```text
[Physical JVM]  ← JVM is the runtime
┌───────────────────────────────────────────────┐
│               JUnit Test Layer                │
│                                               │
│  ┌──────────────────────────┐                 │
│  │  Test Code (@Test etc.)  │ ← Your test classes
│  │  (JUnit Jupiter API)     │
│  │  JVM loads & executes    │
│  └──────────────────────────┘
│                                               │
│  ┌──────────────────────────┐                 │
│  │  Test Engine (Jupiter)   │ ← Discovers & runs tests
│  │  JVM executes reflection,│
│  │  lifecycle & extensions  │
│  │  (logical in-memory      │
│  │   containers for test    │
│  │   instances & extensions)│
│  └──────────────────────────┘
│                                               │
│  ┌──────────────────────────┐                 │
│  │  JUnit Platform           │ ← Launches & coordinates
│  │  JVM orchestrates Launcher│
│  │  test discovery & reporting│
│  │  (logical orchestration) │
│  └──────────────────────────┘
└───────────────────────────────────────────────┘

[Optional Containers Layer]  ← Only if using Spring/Testcontainers
┌───────────────────────────────────────────────┐
│  Spring ApplicationContext / DI Container     │
│  (creates beans, injects dependencies)       │
├───────────────────────────────────────────────┤
│  Testcontainers (Docker containers)          │
│  (e.g., MongoDB, Kafka, Redis)              │
│  Launched on demand for integration testing │
└───────────────────────────────────────────────┘

```