<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Smart Notes - Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-body">
    <div class="auth-card">
        <h1>Smart Notes</h1>
        <p class="subtitle">Login to your account</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <% if (request.getParameter("msg") != null) { %>
            <div class="success"><%= request.getParameter("msg") %></div>
        <% } %>

        <form action="login" method="post">
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required placeholder="Enter email">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required placeholder="Enter password">
            </div>
            <button type="submit" class="btn-primary">Login</button>
        </form>

        <p class="switch-link">Don't have an account? <a href="register.jsp">Register</a></p>
    </div>
</body>
</html>
