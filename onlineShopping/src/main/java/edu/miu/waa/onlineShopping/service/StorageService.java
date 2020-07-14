package edu.miu.waa.onlineShopping.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void saveImage(MultipartFile productImage , Long product_id);

}
