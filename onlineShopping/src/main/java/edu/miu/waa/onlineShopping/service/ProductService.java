package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Product;

import java.util.*;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    List<Product> getAllProducts(Long ProductCategoryId);
    //List<Product> getAllProducts(Long SellerId);
}
