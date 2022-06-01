package com.datagrokr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

        if(LocalDateTime.now().getHour() >= 20){
            return Response.serverError().entity(new HelperEntity(400, "Booking is closed")).build();
        }

        if(restaurantRepository.count().equals("10"))
            return Response.serverError().entity(new HelperEntity(400, "All tables are booked")).build();
        else if(restaurant.getNoOfPeople() == 2 && restaurantRepository.twoPersonsGroup().equals("5"))
            return Response.serverError().entity(new HelperEntity(400,"All tables with 2 people capacity are filled")).build();
        else if(restaurant.getNoOfPeople() == 4 && restaurantRepository.fourPersonsGroup().equals("5"))
            return Response.serverError().entity(new HelperEntity(400,"All tables with 4 people capacity are filled")).build();
        else if(!restaurant.getBookingTime().toLocalDate().isEqual(LocalDate.now()) || restaurant.getBookingTime().toLocalTime().isBefore(LocalTime.of(17, 0)))
            return Response.serverError().entity(new HelperEntity(500, "Booking allowed for curr day only and after 5PM")).build();
        restaurantRepository.addReservation(restaurant);
        return Response.status(Status.OK).entity(restaurant).build();
    }

    public Response updateReservation(Restaurant restaurant){
        if(LocalDateTime.now().getHour() >= 20){
            return Response.serverError().entity(new HelperEntity(400, "Booking is closed")).build();
        }
        else if(restaurant.getNoOfPeople() == 0)
            return Response.serverError().entity(new HelperEntity(400, "at least 1 person is needed")).build();
        else if(restaurant.getBookingTime() == null)
            return Response.serverError().entity(new HelperEntity(400, "Invalid Time")).build();    
        else if(restaurant.getNoOfPeople() == 2 && restaurantRepository.twoPersonsGroup().equals("5"))
            return Response.serverError().entity(new HelperEntity(400,"All tables with 2 people capacity are filled")).build();
        else if(restaurant.getNoOfPeople() == 4 && restaurantRepository.fourPersonsGroup().equals("5"))
            return Response.serverError().entity(new HelperEntity(400,"All tables with 4 people capacity are filled")).build();
        else if(!restaurant.getBookingTime().toLocalDate().isEqual(LocalDate.now()))
            return Response.serverError().entity(new HelperEntity(500, "Booking allowed for curr day only")).build();
        
        return restaurantRepository.updateReservation(restaurant);
    }

    public Response removeReservation(Long id){
        restaurantRepository.deleteReservation(id);
        return Response.status(Status.OK).entity("reservation deleted").build();
    }

    public Response deleteByMobileNo(String mobNo){
        restaurantRepository.deleteByPhoneNumber(mobNo);
        return Response.status(Status.OK).entity("reservation deleted").build();
    }

}
