package edu.miu.waa.onlineShopping.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.repository.ProductCategoryRepository;
import edu.miu.waa.onlineShopping.service.ProductCategoryService;

@Service
public class ProductProductCategoryServiceImpl implements ProductCategoryService {
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
}