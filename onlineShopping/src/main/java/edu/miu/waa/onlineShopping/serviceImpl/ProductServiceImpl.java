package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.domain.Review;
import edu.miu.waa.onlineShopping.repository.ProductRepository;
import edu.miu.waa.onlineShopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : iterable) {
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getProductById(Long productId) {
        Iterable<Product> iterable = productRepository.findAll();
        Product product = null;
        for (Product p : iterable) {
            if(p.getId() == productId)
                product = p;
        }
        return product;/**/
    }

    @Override
    public List<Product> getAllProductsPerCategory(Long ProductCategoryId) {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product p : iterable) {
            if(p.getProductCategory().getId() == ProductCategoryId)
                productList.add(p);
        }
        return productList;
    }

    @Override
    public Set<Review> getProductReviews(Long productId) {
        return getProductById(productId).getReviews();
    }
}
