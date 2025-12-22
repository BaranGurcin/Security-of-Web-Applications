# Java Chat Server and Client

This project implements a simple **multi-client chat system** in Java. It consists of a **server** (`chatServer`) and a **client** (`clientEMMA`), **client** (`clientMARK`) that communicate over TCP sockets. Multiple clients can connect to the server and send messages that are broadcast to all connected clients.

---

## 📁 Project Structure
```bash
Websocket/
└───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───BG
    │   │           └───swp
    │   │               └───lab1
    │   │                       chatServer.java
    │   │                       clientEMMA.java
    │   │                       clientMARK.java
    │   │                       Main.java

```

---

## 🖥 Features

- Multi-client chat system using **TCP sockets**
- Broadcast messages to all connected clients
- Threaded server to handle multiple clients concurrently
- Simple console interface for clients
- Auto-flushing `PrintWriter` for immediate message delivery

---

## ⚙️ Prerequisites

- Java Development Kit (JDK) 17 or higher
- IDE (optional) like VS Code, IntelliJ IDEA, or Eclipse
- Terminal / command prompt

---

## 🚀 How to Run

### 1️⃣ Start the Server

1. Navigate to the project directory.
2. Compile the server:
```bash
javac com/BG/swp/lab1/chatServer.java
