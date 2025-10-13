<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
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

        .checkout-container {
            width: 50%;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .info {
            font-size: 18px;
            margin: 15px 0;
        }

        .checkout-btn {
            background: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .checkout-btn:hover {
            background: #218838;
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

<div class="checkout-container">
    <h2>Order Summary</h2>
    <div class="info">
        <p><strong>Total Amount:</strong> ₹${total}</p>
        <p><strong>Estimated Delivery Date:</strong> ${arrivalDate}</p>
    </div>
    <button class="checkout-btn">Place Order</button>
</div>

</body>
</html>
