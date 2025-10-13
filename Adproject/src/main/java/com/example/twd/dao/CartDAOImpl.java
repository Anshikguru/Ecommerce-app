package com.example.twd.dao;

import com.example.twd.model.CartItem;
import com.example.twd.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        List<CartItem> items = new ArrayList<>();

        try {
            items = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) -> {
                        Product product;
                        try {
                            product = productDAO.getProductById(rs.getInt("product_id"));
                        } catch (Exception e) {
                            product = new Product(rs.getInt("product_id"), "Unknown", 0.0, "images/default.png");
                        }
                        return new CartItem(rs.getInt("cart_id"), userId, product, rs.getInt("quantity"));
                    },
                    userId // pass parameters as varargs
            );

        } catch (Exception e) {
            // Log exception here if needed
        }

        return items != null ? items : new ArrayList<>();
    }

    @Override
    public void addToCart(int userId, int productId, int quantity) {
        // Check if the item already exists in the cart
        String sqlCheck = "SELECT count(*) FROM cart WHERE user_id = ? AND product_id = ?";

        Integer count = jdbcTemplate.queryForObject(
                sqlCheck,
                Integer.class,
                userId,
                productId);

        if (count != null && count > 0) {
            // Update quantity if item exists
            String sqlUpdate = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(sqlUpdate, quantity, userId, productId);
        } else {
            // Insert new item if it does not exist
            String sqlInsert = "INSERT INTO cart(user_id, product_id, quantity) VALUES(?,?,?)";
            jdbcTemplate.update(sqlInsert, userId, productId, quantity);
        }
    }    

    @Override
    public double getCartTotal(int userId) {
        List<CartItem> items = getCartItemsByUserId(userId);
        return items.stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum();
    }
}
