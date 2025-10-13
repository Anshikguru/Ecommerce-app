package com.example.twd.dao;

import com.example.twd.model.CartItem;
import java.util.List;

public interface CartDAO {
    List<CartItem> getCartItemsByUserId(int userId);

    void addToCart(int userId, int productId, int quantity);

    double getCartTotal(int userId);
}
