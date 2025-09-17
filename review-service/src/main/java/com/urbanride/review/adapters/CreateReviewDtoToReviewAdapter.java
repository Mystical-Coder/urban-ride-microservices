package com.urbanride.review.adapters;

import com.urbanride.review.dtos.CreateReviewDto;
import com.urbanride.review.models.Review;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto(CreateReviewDto createReviewDto);

}
