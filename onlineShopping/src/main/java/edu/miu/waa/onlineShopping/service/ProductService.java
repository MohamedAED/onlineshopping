package edu.miu.waa.onlineShopping.service;


import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Review;
import java.util.*;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    List<Product> getAllProductsPerCategory(Long ProductCategoryId);
    Set<Review> getProductReviews(Long productId);
}
