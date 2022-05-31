package com.datagrokr.service;

import com.datagrokr.entity.Restaurant;
import com.datagrokr.repository.RestaurantRepository;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class BookingService {
    
    private RestaurantRepository restaurantRepository;

    public BookingService(){
        restaurantRepository = new RestaurantRepository();
    }

    public String greetUser(){
        return "Welcome to Domino's";
    }

    public Response bookTable(Restaurant restaurant){
        restaurantRepository.addReservation(restaurant);
        return Response.status(Status.OK).entity(restaurant).build();
    }

    public Response removeReservation(Long id){
        restaurantRepository.deleteReservation(id);
        return Response.status(Status.OK).entity("reservation deleted").build();
    }

}
