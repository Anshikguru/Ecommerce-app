package com.example.twd.service;

import com.example.twd.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(int id);
}
