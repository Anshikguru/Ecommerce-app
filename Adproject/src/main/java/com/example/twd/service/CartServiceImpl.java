package com.example.twd.service;

import com.example.twd.dao.CartDAO;
import com.example.twd.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        return cartDAO.getCartItemsByUserId(userId);
    }

    @Override
    public void addToCart(int userId, int productId, int quantity) {
        cartDAO.addToCart(userId, productId, quantity);
    }

    @Override
    public double getCartTotal(int userId) {
        return cartDAO.getCartTotal(userId);
    }
}
