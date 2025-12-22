# Todo App with Cookies and Sessions (Student Project)

This project is a **continuation of the previous Todo HTTP lab**. The main goal was to **understand cookies and sessions** and how data can be stored between requests using HTTP.

The application is still simple on purpose and focuses on **learning how HTTP state works**, not on advanced backend or database usage.

---

## 🎯 Purpose of the Assignment

The objectives of this lab are to:

* Understand how **cookies** work in HTTP
* Learn how data can be **stored and reused between requests**
* Practice using cookies from both **backend and frontend**
* Reinforce understanding of **HTTP methods** (GET, POST, PUT, DELETE)
* Build a small full example using **Java backend + HTML/JS frontend**

---

## 📝 What the Application Does

This is a simple **Todo application** where:

* Todos are created, updated, and deleted
* Data is stored using **cookies** instead of a database
* Todos remain available after page reloads
* A theme (light/dark) is also saved using cookies

Each todo contains:

* `id`
* `text`
* `completed`

---

## 🌐 Backend (Java)

* Handles HTTP requests using a `TodoController`
* Uses cookies to store todo data
* Processes JSON request bodies
* Demonstrates basic session-like behavior using cookies

---

## 💻 Frontend (HTML + JavaScript)

* Simple HTML and CSS interface
* Uses JavaScript to:

  * Read cookies
  * Store todos in cookies
  * Restore todos on page load
  * Save selected theme (light/dark)

---

## 📚 What I Learned

* Why HTTP is stateless by default
* How cookies help store state
* How frontend and backend can share data using cookies
* How sessions are simulated using cookies
* How a simple REST-style Todo app works end-to-end

---

## ⚠️ Note

This project is **for educational purposes only**. Cookies are used for simplicity and are not suitable for storing sensitive data in real applications.
