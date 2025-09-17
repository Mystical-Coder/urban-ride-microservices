package com.urbanride.booking.services;

import com.urbanride.booking.dto.*;
import com.urbanride.booking.apis.LocationServiceApi;
import com.urbanride.booking.apis.UberSocketApi;
import com.urbanride.booking.repositories.BookingRepository;
import com.urbanride.booking.repositories.DriverRepository;
import com.urbanride.booking.repositories.PassengerRepository;
import org.example.uberprojectentityservice.models.Booking;
import org.example.uberprojectentityservice.models.BookingStatus;
import org.example.uberprojectentityservice.models.Driver;
import org.example.uberprojectentityservice.models.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final PassengerRepository passengerRepository;

    private final BookingRepository bookingRepository;

    private final RestTemplate restTemplate;

    private final LocationServiceApi locationServiceApi;

    private final DriverRepository driverRepository;

    private final UberSocketApi uberSocketApi;

//    private static final String LOCATION_SERVICE = "http://localhost:7477";


    public BookingServiceImpl(PassengerRepository passengerRepository, BookingRepository bookingRepository, LocationServiceApi locationServiceApi, DriverRepository driverRepository, UberSocketApi uberSocketApi) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
        this.uberSocketApi = uberSocketApi;
        this.restTemplate = new RestTemplate();
        this.locationServiceApi = locationServiceApi;
        this.driverRepository = driverRepository;
    }

    @Override
    public CreateBookingResponseDto createBooking(CreateBookingDto createBookingDto) {
        Optional<Passenger> passenger = passengerRepository.findById(createBookingDto.getPassengerId());
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(createBookingDto.getStartLocation())
                .endLocation(createBookingDto.getEndLocation())
                .passenger(passenger.get())
                .build();

        Booking newBooking = bookingRepository.save(booking);

        NearbyDriversRequestDto request = new NearbyDriversRequestDto().builder()
                .latitude(createBookingDto.getStartLocation().getLatitude())
                .longitude(createBookingDto.getStartLocation().getLongitude())
                .build();

//        ResponseEntity<DriverLocationDto[]> result = restTemplate.postForEntity(LOCATION_SERVICE + "/api/location/nearby/drivers", request,DriverLocationDto[].class);


//        if(result.getStatusCode().is2xxSuccessful() && result.getBody() != null){
//            List<DriverLocationDto> driverLocation = Arrays.asList(result.getBody());
//            driverLocation.forEach(driverLocationDto -> {
//                System.out.println(driverLocationDto.getDriverId());
//            });
//
//        }

        processNearbyDriverAsync(request, createBookingDto.getPassengerId(), newBooking.getId());  //Async call


        return CreateBookingResponseDto.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus().toString())
                .build();
    }

    @Override
    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto bookingRequestDto, Long bookingId) {
        Optional<Driver> driver = driverRepository.findById(bookingRequestDto.getDriverId().get());
//        TODO: if(driver.isPresent() && driver.get().isAvailable())
        bookingRepository.updateBookingStatusAndDriverById(bookingId, BookingStatus.SCHEDULED, driver.get());
//        TODO: driverRepository.update -> make it unavailable
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return UpdateBookingResponseDto.builder()
                .bookingId(bookingId)
                .status(booking.get().getBookingStatus())
                .driver(Optional.ofNullable(booking.get().getDriver()))
                .build();
    }

    private void processNearbyDriverAsync(NearbyDriversRequestDto requestDto, Long passengerId, Long bookingId) {
        Call<DriverLocationDto[]> call = locationServiceApi.getNearbyDrivers(requestDto);

        call.enqueue(new Callback<DriverLocationDto[]>() {
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DriverLocationDto> driverLocation = Arrays.asList(response.body());
                    driverLocation.forEach(driverLocationDto -> {
                        System.out.println(driverLocationDto.getDriverId() + " " + " lat: " +
                                driverLocationDto.getLatitude() + " long: " + driverLocationDto.getLongitude());
                    });

                    raiseRideRequestAsync(RideRequestDto.builder().passengerId(passengerId).bookingId(bookingId).build());

                } else {
                    System.out.println("Request failed " + response.message());
                }
            }

            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void raiseRideRequestAsync(RideRequestDto requestDto){
        Call<Boolean> call = uberSocketApi.raiseRideRequest(requestDto);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null){
                    Boolean result = response.body();
                    System.out.println("Driver response is "+ result.toString());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
