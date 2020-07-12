package edu.miu.waa.onlineShopping.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.miu.waa.onlineShopping.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.repository.ProductRepository;
import edu.miu.waa.onlineShopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public void deleteById(Long product_id) {
		productRepository.deleteById(product_id);
	}
	
	@Override
	public Product findById(Long product_id) {
		return productRepository.findById(product_id).get();
	}
	
	@Override
	public Set<Product> findProductsByCategoryId(Long category_id){
		return productRepository.findByCategoryId(category_id);
	}
	
	@Override
	public Set<Product> findProductsBySellerId(Long seller_id){
		return productRepository.findByCategoryId(seller_id);
	}


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
		return product;
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
