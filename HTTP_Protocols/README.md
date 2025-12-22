# Simple Todo HTTP API (Student Project)

This project was created as a **university lab assignment**. The goal was to build a **very simple Todo application** to understand how **HTTP protocols and methods** work in practice.

The focus of this lab is not frontend or databases, but **handling HTTP requests and responses** using Java.

---

## 🎯 Purpose of the Assignment

The main objectives of this lab are to:

* Understand basic **HTTP methods** (GET, POST, PUT, DELETE)
* Learn how a **controller** handles different request types
* Work with **JSON request and response bodies**
* Practice sending correct **HTTP status codes**
* Understand how a simple REST-like API works

---

## 📝 What the Application Does

This project implements a simple **Todo API**:

* Stores todos in an **in-memory list**
* Uses JSON for data exchange
* Does not use a database (for simplicity)

Each todo has:

* `id`
* `text`
* `completed`

---

## 🌐 Supported HTTP Methods

* **GET** – Returns all todos as JSON
* **POST** – Creates a new todo
* **PUT** – Updates an existing todo
* **DELETE** – Deletes a todo by ID

Each method returns appropriate HTTP status codes such as `200 OK`, `201 Created`, `400 Bad Request`, and `404 Not Found`.

---

## 📚 What I Learned

* How HTTP methods are handled on the server side
* How to read request bodies and return JSON responses
* How REST-style APIs work at a basic level
* Why correct status codes are important

---

## ⚠️ Note

This project is **for learning purposes only**. Data is stored in memory and will be lost when the application stops.
