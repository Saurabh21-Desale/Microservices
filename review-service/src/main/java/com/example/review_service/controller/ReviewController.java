package com.example.review_service.controller;

import com.example.review_service.messaging.ReviewMessageProducer;
import com.example.review_service.model.Review;
import com.example.review_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public List<Review> getAllReview(@RequestParam Long companyId){
        return reviewService.getAllReviews(companyId);
    }

    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable Long reviewId){
        return reviewService.getReview(reviewId);
    }

    @PostMapping
    public String addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            reviewMessageProducer.sendMessage(review);
            return "Review Created Successfully!!!";
        }
        //reviewMessageProducer.sendMessage(review);
        return "Review not saved";
    }

    @PutMapping("/{reviewId}")
    public String updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        reviewService.updateReview(reviewId, review);
        return "Review Updated Successfully!!!";
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return "Review Deleted Successfully!!!";
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

}
