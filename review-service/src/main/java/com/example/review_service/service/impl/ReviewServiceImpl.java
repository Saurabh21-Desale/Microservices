package com.example.review_service.service.impl;

import com.example.review_service.model.Review;
import com.example.review_service.repository.ReviewRepository;
import com.example.review_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(@RequestParam Long companyId) {
        Review review = reviewRepository.findById(companyId).orElseThrow();
        return reviewRepository.findAll();
    }

    @Override
    public Review getReview(Long companyId) {
        return reviewRepository.findById(companyId).orElseThrow();
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        review.setCompanyId(companyId);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public void updateReview(Long id, Review updateReview) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.setTitle(updateReview.getTitle());
        review.setDescription(updateReview.getDescription());
        review.setRating(updateReview.getRating());
        review.setCompanyId(updateReview.getCompanyId());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
