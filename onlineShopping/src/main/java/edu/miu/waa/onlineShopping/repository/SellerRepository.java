package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.Review;
import edu.miu.waa.onlineShopping.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
}
