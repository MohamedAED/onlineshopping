package edu.miu.waa.onlineShopping.serviceImpl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.miu.waa.onlineShopping.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	ServletContext servletContext;

	@Override
	public void saveImage(MultipartFile productImage, Long product_id) {

		if (productImage != null && !productImage.isEmpty()) {
			try {

				File file = new File(servletContext.getRealPath("product/") + product_id + ".png");
				productImage.transferTo(file);

			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
	}

}
