package com.urbanride.booking.apis;

import com.urbanride.booking.dto.DriverLocationDto;
import com.urbanride.booking.dto.NearbyDriversRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationServiceApi {

    @POST("api/location/nearby/drivers")
    Call<DriverLocationDto[]> getNearbyDrivers(@Body NearbyDriversRequestDto requestDto);

}
