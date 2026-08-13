# Smart Notes - Java Full Stack Project

Simple Notes application built with **Java Servlets + JSP + JDBC + MySQL** (No Spring Boot, No Maven).

## Features
- User Registration & Login
- Create, View, Delete Notes
- Add Tags
- Pin important notes
- Search notes
- Session based authentication

## Tech Stack
- Java
- Servlets & JSP
- JDBC
- MySQL
- HTML, CSS, JavaScript
- Apache Tomcat

## Project Structure
```
SmartNotes/
├── src/com/smartnotes/
│   ├── model/
│   ├── dao/
│   ├── util/
│   └── servlet/
├── WebContent/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── lib/          ← Put mysql-connector-j jar here
│   ├── css/
│   ├── login.jsp
│   ├── register.jsp
│   └── home.jsp
└── database/
    └── smartnotes.sql
```
