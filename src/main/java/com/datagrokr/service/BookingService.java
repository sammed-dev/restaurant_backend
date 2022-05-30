package com.datagrokr.service;

import com.datagrokr.repository.RestaurantRepository;

public class BookingService {
    
    RestaurantRepository restaurantRepository;

    public BookingService(){
        restaurantRepository = new RestaurantRepository();
    }

    public String greetUser(){
        return "Welcome to Domino's";
    }

}
