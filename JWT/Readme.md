# JWT Validation Filter Project

This project demonstrates how to **validate JSON Web Tokens (JWTs)** for HTTP requests using a filter. It is designed as **learning exercise** for understanding JWT-based authorization.

---

## 🎯 Purpose

The main objectives of this project are to:

- Learn how to **validate JWT tokens** in HTTP requests
- Protect **specific API endpoints** using a filter
- Understand **token-based authorization** in a backend service
- Practice handling **HTTP status codes** and error responses

---

## 📝 Features

- Filter checks **protected paths** in incoming HTTP requests
- Validates **JWT tokens** in the `Authorization` header
- Returns `401 Unauthorized` for missing, invalid, or expired tokens
- Passes requests along the chain if the token is valid
- Uses **JSON responses** for error messages

---

## 💻 Implementation

- Written in **Java**
- Uses `JwtService.validateToken(token)` to check token validity
- Uses a **Filter pattern** to intercept requests
- Can be used as referance to **full authentication and authorization systems**

---
