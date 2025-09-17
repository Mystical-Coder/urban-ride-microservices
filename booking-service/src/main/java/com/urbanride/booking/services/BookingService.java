package com.urbanride.booking.services;

import com.urbanride.booking.dto.CreateBookingDto;
import com.urbanride.booking.dto.CreateBookingResponseDto;
import com.urbanride.booking.dto.UpdateBookingRequestDto;
import com.urbanride.booking.dto.UpdateBookingResponseDto;


public interface BookingService {
    CreateBookingResponseDto createBooking(CreateBookingDto booking);

    UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto bookingRequestDto, Long bookingId);

}
