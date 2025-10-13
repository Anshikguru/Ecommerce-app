package com.example.twd.service;

import com.example.twd.model.CartItem;
import java.util.List;

public interface CartService {
    List<CartItem> getCartItemsByUserId(int userId);

    void addToCart(int userId, int productId, int quantity);

    double getCartTotal(int userId);
}
