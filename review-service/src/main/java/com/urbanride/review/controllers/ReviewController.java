package com.urbanride.review.controllers;

import com.urbanride.review.adapters.CreateReviewDtoToReviewAdapterImpl;
import com.urbanride.review.dtos.CreateReviewDto;
import com.urbanride.review.dtos.ReviewDto;
import com.urbanride.review.models.Review;
import com.urbanride.review.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    private ReviewService reviewService;

    private CreateReviewDtoToReviewAdapterImpl createReviewDtoToReviewAdapter;


    public ReviewController(ReviewService reviewService,
                            CreateReviewDtoToReviewAdapterImpl createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request) {
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingReview == null) {
            return new ResponseEntity<>("Invalid arguments",HttpStatus.BAD_REQUEST);
        }
        Review review = this.reviewService.publishReview(incomingReview);
        ReviewDto reviewDto = ReviewDto.builder()
                .Id(review.getId())
                .bookId(review.getBooking().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
            if (!isDeleted) return new ResponseEntity<>("Unable to delete Review", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request) {
        try {
            Review review = this.reviewService.updateReview(reviewId, request);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}