package com.datagrokr.service;

import com.datagrokr.entity.HelperEntity;
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

        if(restaurantRepository.count().equals("10"))
            return Response.serverError().entity(new HelperEntity(400, "All tables are booked")).build();
        else if(restaurant.getNoOfPeople() == 2 && restaurantRepository.twoPersonsGroup().equals("5"))
            return Response.serverError().entity("All tables with 2 people capacity are filled").build();
        else if(restaurant.getNoOfPeople() == 4 && restaurantRepository.fourPersonsGroup().equals("5")){
            System.out.println("4 people booking");
            return Response.serverError().entity("All tables with 4 people capacity are filled").build();
        }
        restaurantRepository.addReservation(restaurant);
        return Response.status(Status.OK).entity(restaurant).build();
    }

    public Response updateReservation(Restaurant restaurant){
        return restaurantRepository.updateReservation(restaurant);
    }

    public Response removeReservation(Long id){
        restaurantRepository.deleteReservation(id);
        return Response.status(Status.OK).entity("reservation deleted").build();
    }

}
