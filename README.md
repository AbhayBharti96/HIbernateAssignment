# 🏥 Hospital Management ERP System  
### Hibernate + JPA + MySQL | Enterprise ORM Project

---

## 📌 Project Overview

The **Hospital Management ERP System** is a complete enterprise-level backend application built using **Java, Hibernate ORM (JPA), and MySQL**.

This project demonstrates all **5 major JPA relationship mappings** using real-world healthcare domain modeling:

- Patients  
- Doctors  
- Departments  
- Appointments  
- Prescriptions  
- Medical Records  

It includes full **CRUD operations**, cascading behavior, lazy loading, JPQL queries, and relationship management.

---

## 🚀 Technologies Used

- ☕ Java 21  
- 🗄 Hibernate ORM 6  
- 📦 Jakarta Persistence (JPA 3.0)  
- 🐬 MySQL 8  
- 🔧 Maven  
- 💻 IntelliJ IDEA  

---

## 🧩 JPA Relationship Types Implemented

| Mapping Type | Relationship | Example |
|--------------|-------------|----------|
| 🔹 Unidirectional One-to-One | `@OneToOne` | Patient → MedicalRecord |
| 🔹 Bidirectional One-to-Many | `@OneToMany / @ManyToOne` | Department ↔ Doctor |
| 🔹 Unidirectional One-to-Many | `@OneToMany + @JoinColumn` | Doctor → Appointment |
| 🔹 Optional One-to-One | `@OneToOne(optional=true)` | Appointment → Prescription |
| 🔹 Bidirectional Many-to-Many | `@ManyToMany + @JoinTable` | Doctor ↔ Patient |

---

## ⚙️ Features Implemented

✔ Entity Mapping using JPA Annotations  
✔ Cascade Operations  
✔ Dirty Checking  
✔ Lazy Loading  
✔ JOIN FETCH to solve LazyInitializationException  
✔ Optional & Mandatory Relationship Handling  
✔ Many-to-Many Join Table Management  
✔ JPQL Queries  
✔ Helper Methods for Bidirectional Consistency  

---

## 🗄 Database Schema Overview

Tables Created Automatically:

- `patient`  
- `medical_record`  
- `department`  
- `doctor`  
- `appointment`  
- `prescription`  
- `patient_doctors` (Join Table)  

---

## 🧪 How to Run the Project

### 1️⃣ Create Database

```sql
CREATE DATABASE hospital_erp;
```

---

### 2️⃣ Configure Database Credentials

Update `persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="your_password"/>
```

---

### 3️⃣ Run Application

Run:

```
MainRunner.java
```

All CRUD operations will execute automatically.

---

## 👨‍💻 Author

**Abhay Bharti**  
B.Tech Information Technology  
Lovely Professional University  

---

## 🎯 Resume Description

Developed a Hospital ERP backend system using Java, Hibernate (JPA), and MySQL implementing all 5 JPA relationship types with full CRUD operations, cascading behavior, and JPQL queries.

---
