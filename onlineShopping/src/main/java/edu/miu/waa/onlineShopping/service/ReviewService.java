package edu.miu.waa.onlineShopping.service;


import edu.miu.waa.onlineShopping.domain.Review;

import java.util.List;

public interface ReviewService {
    void save(Review review);
    List<Review> findUnapprovedReviews();
    void approveReview(Long id, String statusId);
}
