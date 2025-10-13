<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body{font-family:Arial, sans-serif;background:#f4f4f4;display:flex;justify-content:center;align-items:center;height:100vh;margin:0}
        .login-container{background:#fff;padding:20px 25px;border-radius:8px;box-shadow:0 2px 10px rgba(0,0,0,0.1);width:320px}
        h2{text-align:center;margin-bottom:20px;color:#333}
        .form-group{margin-bottom:15px}
        label{display:block;font-size:14px;margin-bottom:5px;color:#555}
        input{width:100%;padding:8px;border:1px solid #ccc;border-radius:5px;font-size:14px}
        button{width:100%;padding:10px;background:#007BFF;border:none;color:#fff;font-size:16px;border-radius:5px;cursor:pointer}
        button:hover{background:#0056b3}
        .error{color:#b00020;background:#fee; padding:8px;border-radius:4px;margin-bottom:12px;text-align:center}
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>

        <!-- show DB/login errors -->
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/handleLogin" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <!--prefill username if available (from request param or controller)-->
                <input type="text" id="username" name="username" placeholder="Enter username" required value="${username != null ? username : param.username}" />
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter password" required />
            </div>

            <div class="form-group">
                <button type="submit">Login</button>
            </div>
        </form>
    </div>
</body>
</html>
