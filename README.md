---

# Blogging API Backend with Spring Boot

## 🚀 Project Overview

This project is a **Blogging API Backend** built using **Spring Boot**. It offers full **CRUD operations** for managing users, posts, categories, and comments. The API is secured with **JWT (JSON Web Token) Authentication**, and it is fully documented using **Swagger** for easy access and testing of endpoints.

### 📚 Key Features
- **User Management**: Registration, login, and full CRUD operations for users, secured with JWT authentication.
- **Post Management**: CRUD operations for posts, with posts organized by categories.
- **Category Management**: CRUD functionality for managing post categories.
- **Comment Management**: Add, view, edit, and delete comments on posts.

## 🔧 Tech Stack

- **Spring Boot**: For building RESTful services and handling business logic.
- **MySQL Workbench**: Used for database schema design and management of relationships between entities (users, posts, categories, comments).
- **JWT Authentication**: Ensures secure access to protected API endpoints.
- **Swagger**: Integrated for API documentation, allowing developers to explore and test the endpoints with an interactive interface.
- **Railway Cloud**: Used to deploy the API, providing seamless and scalable cloud hosting.

## ⚙️ Project Structure

1. **API Endpoints**: 
   - **User Management**: Register, login, update profile, delete account.
   - **Post Management**: Create, read, update, delete posts. Posts are organized by categories.
   - **Category Management**: Create, read, update, delete categories.
   - **Comment Management**: Users can add, edit, and delete comments on posts.

2. **Security**:
   - JWT Authentication ensures only authorized users can access sensitive data.
   - Secured endpoints using Spring Security.

3. **Documentation**:
   - API documented using **Swagger** for easy understanding, testing, and exploring.
   - Swagger UI accessible for a detailed view of available endpoints and their functionalities.

## 🛠️ Installation & Setup

### Prerequisites:
- Java 8 or higher
- MySQL
- Maven

### Steps to Run:
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/blogging-api-backend.git
   ```
2. Navigate into the project directory:
   ```bash
   cd blogging-api-backend
   ```
3. Create a database in MySQL:
   ```sql
   CREATE DATABASE blog_api;
   ```
4. Update `application.properties` with your database configuration:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_api
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   ```
5. Run the project using Maven:
   ```bash
   mvn spring-boot:run
   ```
6. Access the Swagger UI at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 🔐 Security

The application uses **JWT (JSON Web Token)** for securing the API endpoints. Each user is required to authenticate using a valid token before accessing protected resources.

## 🌐 Deployment

The API is deployed on **Railway Cloud**, making it scalable and accessible for real-world use cases. You can explore the live API via its cloud-hosted Swagger UI.

## 🚀 Future Enhancements

Planned features include:
- **OAuth2 Authentication** for social logins.
- **File uploads for posts**, allowing users to add images or files.
- A **React frontend** to turn the API into a full-stack blogging platform.

---

Let me know if you'd like any adjustments or additional information!
