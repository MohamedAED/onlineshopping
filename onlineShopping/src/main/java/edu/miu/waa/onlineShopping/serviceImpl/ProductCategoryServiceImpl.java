package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.repository.ProductCategoryRepository;
import edu.miu.waa.onlineShopping.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAllProductCategory() {
        Iterable<ProductCategory> iterable = productCategoryRepository.findAll();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for (ProductCategory productCategory : iterable) {
            productCategoryList.add(productCategory);
        }
        return productCategoryList;
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory getProductCategoryByName(String name) {
        return productCategoryRepository.findByName(name);
    }

    @Override
    public ProductCategory getProductCategoryById(Long categoryId) {
        return productCategoryRepository.findById(categoryId).get();
    }


}
