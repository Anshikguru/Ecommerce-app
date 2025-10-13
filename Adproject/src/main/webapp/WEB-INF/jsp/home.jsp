<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Product Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f5f5f5;
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
            width: 90%;
            margin: 20px auto;
        }

        /* Product Grid */
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
        }

        .product-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 15px;
            text-align: center;
            transition: transform 0.2s;
        }
        .product-card:hover {
            transform: translateY(-3px);
        }

        .product-card img {
            width: 100%;
            max-height: 180px;
            object-fit: contain;
            border-radius: 5px;
        }

        .product-card h3 {
            font-size: 18px;
            margin: 10px 0 5px;
        }

        .product-card p {
            font-size: 16px;
            color: #555;
            margin-bottom: 10px;
        }

        .add-to-cart-btn {
            background: #007BFF;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .add-to-cart-btn:hover {
            background: #0056b3;
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
    <h2>Available Products</h2>
    <div class="product-grid">
        <c:forEach var="product" items="${products}">
            <div class="product-card">
                <img src="${product.imageUrl}" alt="${product.name}">
                <h3>${product.name}</h3>
                <p>Price: ₹${product.price}</p>

                <form action="${pageContext.request.contextPath}/add-to-cart/${product.id}" method="post">
                    <button type="submit" class="add-to-cart-btn">Add to Cart</button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
