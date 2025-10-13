<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        /* Navbar */
        nav {
            background: #333;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        nav a {
            color: white;
            text-decoration: none;
            padding: 8px 16px;
            margin-left: 10px;
            border-radius: 5px;
            transition: background 0.3s ease;
        }
        nav a:hover {
            background: #007BFF;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }
        table th, table td {
            text-align: center;
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background: #007BFF;
            color: white;
        }
        img {
            width: 60px;
            height: auto;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav>
    <div>
        <h2 style="display:inline; color:white;">My Store</h2>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/cart">Cart</a>
        <a href="${pageContext.request.contextPath}/checkout">Checkout</a>
    </div>
</nav>

<div class="container">
    <h2>Your Cart</h2>
    <c:choose>
        <c:when test="${empty cartItems}">
            <p>Your cart is empty!</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Product</th>
                        <th>Price (₹)</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <!-- Using CartItem helper getters -->
                            <td><img src="${pageContext.request.contextPath}/${item.imageUrl}" alt="${item.productName}"></td>
                            <td>${item.productName}</td>
                            <td>${item.price}</td>
                            <td>${item.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
