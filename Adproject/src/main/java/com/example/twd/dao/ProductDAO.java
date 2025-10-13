package com.example.twd.dao;

import com.example.twd.model.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();

    Product getProductById(int id);
}
