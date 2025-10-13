package com.example.twd.model;

public class CartItem {
    private int cartId;
    private int userId;
    private Product product;
    private int quantity;

    // Constructor
    public CartItem(int cartId, int userId, Product product, int quantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

    // Standard Getters and Setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // ✅ Helper Getters for JSP to avoid 500 errors
    public String getProductName() {
        return product != null ? product.getName() : "";
    }

    public double getPrice() {
        return product != null ? product.getPrice() : 0;
    }

    public String getImageUrl() {
        return product != null ? product.getImageUrl() : "";
    }
}
