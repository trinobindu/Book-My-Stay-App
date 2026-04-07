# 🏨 Hotel Booking Management System

## 📌 Overview

The **Hotel Booking Management System** is a console-based Java application that simulates how hotels manage room bookings, availability, and customer requests.

This project follows an **App-Based Learning approach** to demonstrate how **Core Java and Data Structures** are applied in real-world software engineering scenarios.

---

## 🎯 Objective

The goal of this project is to showcase the practical application of data structures in solving common challenges in hotel booking systems, such as:

* Ensuring fair request handling using FIFO principles
* Maintaining real-time room availability
* Preventing double-booking through uniqueness enforcement
* Designing a scalable and maintainable system

---

## 🧠 Learning Approach

The application is built incrementally using multiple **use cases**, where each stage introduces a new concept while reinforcing previously learned ones.

Each stage focuses on:

* ✔ Selecting the appropriate data structure
* ✔ Understanding the real-world problem it solves
* ✔ Identifying limitations and improving design

---

## ⚙️ Features

### 🛏️ Room Management

* Different room types (Standard, Deluxe, Suite)
* Track room availability
* Dynamic allocation and release of rooms

### 📅 Booking System

* FIFO-based booking requests (Queue implementation)
* Customer booking records
* Check-in and check-out handling

### 🚫 Validation & Constraints

* Prevents double-booking of rooms
* Unique booking IDs
* Exception handling for invalid operations

### 🔄 System Operations

* Add / cancel bookings
* View room availability
* Manage booking history

---

## 🧩 Technologies Used

* **Java (Core Java)**
* **Object-Oriented Programming (OOP)**
* **Data Structures**

  * Queue (for request handling)
  * ArrayList / HashSet (for storage and uniqueness)

---

## 📁 Project Structure

```
HotelBookingManagement/
│── Main.java
│── Hotel.java
│── Room.java
│── Booking.java
│── BookingManager.java
│── Exceptions/
│     └── BookingException.java
```

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone <repo-link>
   ```

2. Compile the program:

   ```bash
   javac Main.java
   ```

3. Run the application:

   ```bash
   java Main
   ```

---

## 📚 Key Concepts Covered

* Classes and Objects
* Inheritance & Polymorphism
* Exception Handling
* Java Collections Framework
* Queue (FIFO) implementation
* Data structure application in real systems

---

## 🚀 Future Enhancements

* GUI-based interface (JavaFX/Swing)
* Database integration (MySQL)
* Online booking simulation
* Payment gateway integration

---

## 👨‍💻 Author

TRINOBINDU DAS

---

## 📌 Note

This project focuses on **core logic, system design, and data structure usage** rather than user interface complexity. It is intended for **learning and academic purposes**.

---

⭐ If you find this project useful, consider giving it a star!
