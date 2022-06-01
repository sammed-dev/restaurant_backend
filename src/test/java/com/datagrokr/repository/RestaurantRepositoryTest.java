package com.datagrokr.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import com.datagrokr.entity.Restaurant;

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
        Restaurant restaurant = new Restaurant(21l, "sams", "1111122222", 2, LocalDateTime.of(2022, 6, 1, 18, 00, 00, 00));
        Restaurant response =  restaurantRepository.addReservation(restaurant);
        assertEquals(restaurant, response);
    }
}
