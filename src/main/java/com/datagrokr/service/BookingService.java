package com.datagrokr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        return "Welcome to tasty delight";
    }
    
    public List<Restaurant> findAllReservations(){
        return restaurantRepository.allReservations();
    }

    public Response bookTable(Restaurant restaurant){

        if(LocalDateTime.now().getHour() >= 20){
            return Response.serverError().entity("Booking is closed").build();
        }

        if(restaurantRepository.count().equals("10"))
            return Response.serverError().entity("All tables are booked").build();
        else if(restaurant.getNoOfPeople() == 2 && restaurantRepository.twoPersonsGroup().equals("5"))
            return Response.serverError().entity("All tables with 2 people capacity are filled").build();
        else if(restaurant.getNoOfPeople() == 4 && restaurantRepository.fourPersonsGroup().equals("5"))
            return Response.serverError().entity("All tables with 4 people capacity are filled").build();
        else if(!restaurant.getBookingTime().toLocalDate().isEqual(LocalDate.now())) 
            return Response.serverError().entity("Booking allowed for curr day only").build();
        restaurantRepository.addReservation(restaurant);
        return Response.status(Status.OK).entity(restaurant).build();
    }

    public Response updateReservation(Restaurant restaurant){
        if(LocalDateTime.now().getHour() >= 20){
            return Response.serverError().entity("Booking is closed").build();
        }
        
        if(restaurant.getNoOfPeople() == 0)
            return Response.serverError().entity("at least 1 person is needed").build();
        else if(restaurant.getBookingTime() == null)
            return Response.serverError().entity("Invalid Time").build();    
        else if(restaurant.getNoOfPeople() == 2 && restaurantRepository.twoPersonsGroup().equals("5"))
            return Response.serverError().entity("All tables with 2 people capacity are filled").build();
        else if(restaurant.getNoOfPeople() == 4 && restaurantRepository.fourPersonsGroup().equals("5"))
            return Response.serverError().entity("All tables with 4 people capacity are filled").build();
        else if(!restaurant.getBookingTime().toLocalDate().isEqual(LocalDate.now()))
            return Response.serverError().entity("Booking allowed for curr day only").build();
        
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

    public Response findReservationById(Long id){
        Restaurant result = restaurantRepository.findById(id);
        return Response.status(Status.OK).entity(result).build();
    }

}
