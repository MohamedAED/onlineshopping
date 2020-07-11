package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.repository.ProductCategoryRepository;
import edu.miu.waa.onlineShopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

}
