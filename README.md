# 🏥 Healthcare Patient Management System - Spring Boot REST API

This project is a secure, role-based Healthcare Patient Management System backend built with **Spring Boot**, **JWT**, **Spring Security**, **MySQL**, and **Swagger UI**. It handles **user registration/login**, **appointment management**, and **medical records**, with strict access control for **Doctor** and **Patient** roles.

---

## 👨‍💻 Developed By

- **Developer:** Danaja Pathmakumara

---

## 🧰 Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Web, Security, Data JPA
- JWT (io.jsonwebtoken)
- MySQL (or H2)
- Swagger OpenAPI (SpringDoc)
- Lombok

---

## 🚀 How to Run

1. **Clone this repo:**

```bash
git clone https://github.com/your-username/healthcare-system.git
cd healthcare-system
```

2. **Set up MySQL:**

```sql
CREATE DATABASE healthcare_db;
```

3. **Configure MySQL credentials in** `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

4. **Run the application:**

```bash
./mvnw spring-boot:run
```

5. **Access Swagger UI:**

- Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- API Docs: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🔐 JWT Authentication

### 🔑 Login

```http
POST /api/auth/login
```

#### Request Body:

```json
{
  "email": "doctor@example.com",
  "password": "1234"
}
```

Copy the token from the response:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

Use this token to access protected endpoints.

---

## 👤 Roles & Access

| Role    | Permissions |
|---------|-------------|
| **DOCTOR**  | Add/view all medical records, view appointments |
| **PATIENT** | Book appointments, view only their records |
| **ADMIN**   | *(optional)* Manage users, appointments, records |

---

## 📬 API Endpoints

### 🔓 Public

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/users/create-with-encryption` | POST | Create user with encrypted password |
| `/api/auth/login` | POST | Login and get JWT token |

### 👩‍⚕️ Doctor (Protected)

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/records` | POST | Add medical record |
| `/api/records` | GET | View all records |
| `/api/appointments/doctor/{id}` | GET | View doctor’s appointments |

### 👨‍🦰 Patient (Protected)

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/appointments` | POST | Book appointment |
| `/api/appointments/patient/{id}` | GET | View patient’s appointments |
| `/api/records/patient/{id}` | GET | View own records only |

---

## 🧪 Postman & Swagger Testing

1. **Create users** via `/api/users/create-with-encryption`
2. **Login** via `/api/auth/login`
3. **Copy token** and use it in:
   - Postman → Authorization → Bearer Token
   - Swagger → Click `Authorize` → Paste token

---

## 🗃 Database Schema (Simplified)

- `users` (id, name, email, password, role)
- `appointments` (id, doctor_id, patient_id, date, time, status)
- `medical_records` (id, doctor_id, patient_id, diagnosis, note, date)

---

## 🧾 License

This project is for academic and learning purposes at NIBM Sri Lanka.
