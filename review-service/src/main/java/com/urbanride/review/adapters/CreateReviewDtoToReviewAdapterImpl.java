package com.urbanride.review.adapters;

import com.urbanride.review.dtos.CreateReviewDto;
import com.urbanride.review.models.Booking;
import com.urbanride.review.models.Review;
import com.urbanride.review.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter{

    private BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {

        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());

        return booking.map(value -> Review.builder()
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .booking(value)
                .build()).orElse(null);
    }
}
