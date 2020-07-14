package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Review;
import edu.miu.waa.onlineShopping.domain.enums.ReviewStatus;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repository.ReviewRepository;
import edu.miu.waa.onlineShopping.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void approveReview(Long id, String statusId) {
        Review review = reviewRepository.findById(id).get();
        if(review == null){
            try {
                throw new SQLException("error while approving the user");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(Integer.parseInt(statusId)==2)
            review.setReviewStatus(ReviewStatus.APPROVED);
        else{
            review.setReviewStatus(ReviewStatus.REJECTED);
        }
        reviewRepository.save(review);
    }
    public List<Review> findUnapprovedReviews(){
        return reviewRepository.findAllUnApprovedReviews();
    }

}
