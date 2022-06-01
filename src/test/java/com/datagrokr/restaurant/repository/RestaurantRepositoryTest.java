package com.datagrokr.restaurant.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import com.datagrokr.entity.Restaurant;
import com.datagrokr.repository.RestaurantRepository;

import org.junit.Before;
import org.junit.Test;

public class RestaurantRepositoryTest {

    RestaurantRepository restaurantRepository;

    public RestaurantRepositoryTest(){

    }

    @Before
    public void setUp(){
        restaurantRepository = new RestaurantRepository();
    }

    @Test
    public void addReservationTest(){
        Restaurant restaurant = new Restaurant("sams", "1111122222", 2, LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        Restaurant response =  restaurantRepository.addReservation(restaurant);
        assertEquals(restaurant, response);
    }

    @Test
    public void updateReservationTest(){
        Restaurant restaurant = new Restaurant("sams", "1111122222", 2, LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        restaurantRepository.addReservation(restaurant);
        Restaurant toBeUpdated = new Restaurant();
        toBeUpdated.setId(restaurant.getId());
        toBeUpdated.setNoOfPeople(4);
        toBeUpdated.setBookingTime(restaurant.getBookingTime());
        assertNotNull(restaurantRepository.updateReservation(toBeUpdated));
    }


    @Test
    public void deleteReservationTest(){
        Restaurant restaurant = new Restaurant("sams", "1111122222", 2, LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        Restaurant restaurant2 = restaurantRepository.addReservation(restaurant);
        restaurantRepository.deleteByPhoneNumber(restaurant.getMobileNo());
        assertNotNull(restaurantRepository.findById(restaurant2.getId()));
    }
}
    