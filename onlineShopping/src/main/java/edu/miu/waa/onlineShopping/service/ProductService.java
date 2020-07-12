package edu.miu.waa.onlineShopping.service;

import java.util.List;
import java.util.Set;

import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Review;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    List<Product> getAllProductsPerCategory(Long ProductCategoryId);
    Set<Review> getProductReviews(Long productId);
}
