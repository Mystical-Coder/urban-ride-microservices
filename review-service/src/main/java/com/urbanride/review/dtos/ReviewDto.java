package com.urbanride.review.dtos;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String content;
    private Double rating;
    private  Long Id;
    private Long bookId;
    private Date createdAt;
    private Date updatedAt;

}
