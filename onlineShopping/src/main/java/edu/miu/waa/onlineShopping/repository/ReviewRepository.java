package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("select r from Review r where r.reviewStatus = 0 ")
    List<Review> findAllUnApprovedReviews();

}
