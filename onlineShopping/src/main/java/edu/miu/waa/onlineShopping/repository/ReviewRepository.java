package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
