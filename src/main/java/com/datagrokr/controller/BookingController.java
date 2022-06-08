package com.datagrokr.controller;

import java.util.List;

import com.datagrokr.entity.Restaurant;
import com.datagrokr.service.BookingService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("restaurant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {
    
    private BookingService bookingService = new BookingService();

    @GET
    @Path("/welcome")
    public String greetUser(){
        return bookingService.greetUser();
    }

    @GET
    @Path("/all")
    public List<Restaurant> findAllReservation(){
        return bookingService.findAllReservations();
    }
    
    @GET
    @Path("/{id}")
    public Response findReservation(@PathParam("id") Long id){
        return bookingService.findReservationById(id);
    }
    
    @POST
    @Path("/book")
    public Response bookTable(Restaurant restaurant){
        return bookingService.bookTable(restaurant);
    } 

    @PUT
    @Path("/update/{id}")
    public Response updateBooking(@PathParam("id") Long id, Restaurant restaurant){
        restaurant.setId(id);
        return bookingService.updateReservation(restaurant);
    }

    @DELETE
    @Path("/delete/{id}")
    public Response removeBooking(@PathParam("id") Long id){
        return bookingService.removeReservation(id);
    }

    @DELETE
    @Path("/delete/mobile/{mobileNo}")
    public Response deleteByMobileNo(@PathParam("mobileNo") String mobNo){
        return bookingService.deleteByMobileNo(mobNo);
    }

}
