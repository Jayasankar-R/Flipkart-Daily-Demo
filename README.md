# 🛒 Flipkart Daily Inventory System (Spring Boot)

This is a simplified backend inventory management system designed as a demo for Flipkart Daily use cases. The application allows for **adding items**, **updating stock**, **searching inventory** with filters, and **viewing grouped inventory** data.

---

## 🚀 Features

- ✅ Add items with brand, category, and price
- ✅ Add stock (inventory quantity) for existing items
- ✅ Search items with optional filters (brand, category, price range)
- ✅ Sort results by price or quantity in ascending or descending order
- ✅ View inventory in a structured format grouped by brand and category
- ✅ DTO-based request/response validation
- ✅ RESTful API design
- ✅ Service, Repository, and Controller layer separation

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Validation
- In-memory store using `ConcurrentHashMap` (for demo)
- Postman (for API testing)

---

## 📁 Project Structure
```
src/
├── controller/ # REST API layer
├── service/ # Business logic
├── repository/ # In-memory data store
├── models/ # Item entity
├── dto/ # Request DTOs for input validation
└── FlipkartDailyDemoApplication.java
```

---

## 📬 API Endpoints

### 1. **Add Item**
```http
POST /api/inventory/add-item?brand=Apple&category=Mobile&price=999
```
### 2. **Add Stock**
```
POST /api/inventory/add-stock?brand=Apple&category=Mobile&qty=10
```
### 3. **Search**
```
GET /api/inventory/search?brand=Apple&category=Mobile&fromPrice=500&toPrice=1500&orderBy=price&order=asc
```
### 4. **Inventory**
```
GET /api/inventory/view
```



