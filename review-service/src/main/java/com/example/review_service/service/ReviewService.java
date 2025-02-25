package com.example.review_service.service;

import com.example.review_service.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId);

    boolean addReview(Long companyId, Review review);

    void updateReview(Long id, Review updateReview);

    void deleteReview(Long id);
}
