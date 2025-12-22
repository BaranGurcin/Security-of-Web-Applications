# Todo Web App with Authentication and Authorization

This project is my Lab 6 assignment where I built a **Todo Web App** with **authentication and authorization**. It's an advanced version of my earlier Todo project, now including **user registration, login, sessions, cookies, and email verification**.

---

## Features

1. **User Registration**
   - Users can register with email and password.
   - Passwords are **hashed** for security.
   - An **activation code** is sent via email to verify the account.
   - Users must activate their account before logging in.

2. **Login & Logout**
   - Only activated users can log in.
   - Uses **sessions** to keep users logged in.
   - Session info stored in cookies (`SESSION_ID`) with security settings:
     - HttpOnly
     - SameSite=Strict
     - Expiration time

3. **Todo App**
   - Add, edit, delete tasks.
   - Tasks are stored **temporarily in memory** and also tracked using **cookies**.
   - UI supports **dark/light theme** with cookie persistence.

4. **Security & Authorization**
   - Passwords hashed and verified securely.
   - Users need to be **authenticated** to access the Todo page.
   - Only logged-in users with valid sessions can perform actions.

5. **Email Service**
   - Sends **HTML email** with activation code.
   - Generates **secure random activation codes**.

---

## How It Works

1. **Register**
   - User submits email and password.
   - Server hashes password and saves user with inactive status.
   - Activation code is sent via email.

2. **Activate Account**
   - User enters activation code from email.
   - Server verifies code and expiry time.
   - Account is marked as active.

3. **Login**
   - User submits email and password.
   - Server verifies credentials and activation status.
   - If valid, server creates a **session** and sets a secure cookie.

4. **Todo App**
   - Only accessible if logged in.
   - Tasks can be added, edited, or deleted.
   - Todos are stored in **session cookies** for persistence.

---

## Tech Used

- Java 17
- Custom **HTTP Server** and Controller framework
- JSON handling: **Jackson**
- Email sending: **JavaMail (SMTP)**
- Authentication & Authorization
- Cookie & Session management
- HTML/CSS/JS frontend

---

## Learning Outcomes

- How to **hash passwords** and verify them securely.
- How to manage **sessions and cookies** in a web application.
- How to implement **account activation via email**.
- Understanding of **HTTP methods** (`GET`, `POST`, `PUT`, `DELETE`) in real apps.
- How to **connect frontend and backend** for a simple web app.

---

## How to Run

1. Configure SMTP settings in `application.properties`.
2. Run the Java server.
3. Open the browser and register a user.
4. Check your email for activation code.
5. Activate account and log in to access the Todo app.

---

## Notes

- This project is an **educational exercise** to understand web security, HTTP, sessions, and authentication.
- Todos are stored in memory and **not persisted** in a database for simplicity.
