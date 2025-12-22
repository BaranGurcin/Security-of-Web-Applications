# Filter Mechanism and Basic Authentication

This project was created as a **student-level Java HTTP filter implementation**. The main purpose is to understand how **filters**, **filter chains**, **logging**, and **Basic Authentication** work in a simple backend system.

---

## 📌 Project Overview

In this project, HTTP requests pass through a **Filter Chain** before reaching the main handler. Each filter has a specific responsibility, such as logging or authentication.

Main components:

* **Filter** – Interface for all filters
* **FilterChain** – Executes filters one by one
* **Auth Filter** – Checks user credentials using Basic Authentication
* **Logging Filter** – Logs incoming requests and outgoing responses
* **SecurityContext** – Stores authenticated user data

Request flow:

```
HTTP Request → Logging Filter → Auth Filter → Request Handler
```

---

## 🧩 Filter Interface

```java
@FunctionalInterface
public interface Filter {
    void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception;
}
```

* All filters implement this interface
* Each filter decides whether the request should continue
* If `chain.doFilter(...)` is not called, the request stops there

---

## 🔗 FilterChain

`FilterChain` controls the order in which filters are executed.

### What it does

* Calls filters one by one
* Keeps track of the current filter position
* Allows a filter to stop further processing

### Important Methods

* `doFilter(...)` – Executes the next filter
* `isFullyProcessed()` – Checks if all filters were executed

### Example Usage

```java
List<Filter> filters = FilterChain.builder()
    .addFilter(new Logging())
    .addFilter(new Auth())
    .build();
```

---

## 🔐 Auth Filter (Basic Authentication)

The `Auth` filter is responsible for **user authentication** using HTTP Basic Authentication.

### How authentication works

1. The filter checks the `Authorization` header
2. It verifies that the `Basic` authentication scheme is used
3. Credentials are decoded from Base64 format
4. Username and password are extracted
5. Credentials are compared with predefined values
6. If authentication is successful, the user is stored in `SecurityContext`
7. If authentication fails, the request is rejected

### Test Credentials

```text
Username: Aegon
Password: keykeykey
```

> ⚠️ This is **only for learning purposes**. In real applications, credentials should be stored securely and never hardcoded.

### Failed Authentication

* HTTP Status Code: `401 Unauthorized`
* Header returned: `WWW-Authenticate: Basic realm="Restricted"`

---

## 📝 Logging Filter

The `Logging` filter logs basic information about each request and response.

### Logged data

* HTTP method (GET, POST, etc.)
* Request path
* Response status code and message
* Timestamp

This filter is usually placed at the beginning of the filter chain so that all requests are logged.

---

## 🎯 Purpose of the Project

The main goal of this project is to:

* Understand how filter chains work
* Learn the basics of HTTP authentication
* Practice clean Java code structure
* Simulate a simple backend security mechanism

This project is intended as a **university lab / learning exercise**.
