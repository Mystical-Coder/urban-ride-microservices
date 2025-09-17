package com.urbanride.review;

import com.urbanride.review.adapters.CreateReviewDtoToReviewAdapterImpl;
import com.urbanride.review.controllers.ReviewController;
import com.urbanride.review.dtos.CreateReviewDto;
import com.urbanride.review.models.Booking;
import com.urbanride.review.models.Review;
import com.urbanride.review.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {


    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;


    @Mock
    private CreateReviewDtoToReviewAdapterImpl createReviewDtoToReviewAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindReviewById_Success(){

        long reviewId = 1L;
        Review mockReview = Review.builder().build();
        mockReview.setId(reviewId);

//        mocking
        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(mockReview));

        ResponseEntity<?> response = reviewController.findReviewById(reviewId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Review>  returnedReview = (Optional<Review>)response.getBody();
        assertEquals(reviewId, returnedReview.get().getId());
    }


    @Test
    public void testPublishReview_Success(){

        CreateReviewDto requestDto = new CreateReviewDto();
        Booking booking = new Booking();
        booking.setId(1L);
        requestDto.setBookingId(booking.getId());

        Review incomingReview = Review.builder()
                .content("Test review content")
                .rating(5.0)
                .booking(booking)
                .build();

        when(createReviewDtoToReviewAdapter.convertDto(requestDto)).thenReturn(incomingReview);

        Review savedReview = Review.builder()
                .content(incomingReview.getContent())
                .rating(incomingReview.getRating())
                .booking(incomingReview.getBooking())
                .build();

        when(reviewService.publishReview(incomingReview)).thenReturn(savedReview);

        ResponseEntity<?> response = reviewController.publishReview(requestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }



}
