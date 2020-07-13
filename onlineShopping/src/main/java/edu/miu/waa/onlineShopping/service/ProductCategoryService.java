package edu.miu.waa.onlineShopping.service;


import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.domain.Seller;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllProductCategory();
    void save(ProductCategory productCategory) ;

    ProductCategory getProductCategoryByName(String name);
}

