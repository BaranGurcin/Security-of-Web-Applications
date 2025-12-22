# Encoding - Encryption - Hashing

This project was created as a **university lab assignment**. The main goal is to **solve given JUnit tests** in order to understand basic concepts related to **encoding, encryption, and hashing** in Java.

---

## 🎯 Goal of the Lab

The purpose of this lab is to:

* Learn the difference between **encoding, encryption, and hashing**
* Understand how **Base64 MIME encoding** works
* Practice **symmetric and asymmetric encryption**
* Implement **password hashing** using secure algorithms
* Improve skills in reading and understanding **JUnit tests**

---

## 🧪 How the Assignment Works

* The expected behavior is defined in **JUnit test classes**
* Our task is to read the tests and implement the correct logic
* If all tests pass, the implementation is correct

---

## 📦 Main Parts of the Project

### 🔡 MIME Encoding

* Uses `java.util.Base64` (MIME)
* Handles long and short strings correctly
* Wraps long output at 76 characters

### 🔐 Symmetric Encryption

* Algorithms: AES, DES, Blowfish
* Encrypts and decrypts text using the same key

### 🔑 Asymmetric Encryption

* Uses RSA
* Encrypts with public key, decrypts with private key

### 🔒 Password Hashing

* Uses SHA-256 and SHA-512
* Produces hexadecimal hashes
* Validates passwords by comparing hashes

### 👤 User Equality

* Correct implementation of `equals()` and `hashCode()`
* Required for proper use in `HashMap` and `HashSet`

---

## 📚 What I Learned

* How cryptographic concepts differ from each other
* How encryption and hashing work in practice
* How to follow tests to implement correct functionality
* Why object equality matters in Java

---

## ⚠️ Note

This project is **for learning purposes only** and not intended for real-world security use.
